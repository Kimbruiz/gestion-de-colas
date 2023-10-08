package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.client.EssiConsulta;
import essalud.gob.pe.gestionatencionservice.client.EssiPaciente;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.consulta.*;
import essalud.gob.pe.gestionatencionservice.dto.essi.*;
import essalud.gob.pe.gestionatencionservice.service.ConsultaService;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final EssiConsulta _essiConsulta;
    private final EssiPaciente _essiPaciente;

    @SneakyThrows
    @Override
    public ResponseDto<ConsultarResponseDto> consultar(ConsultarRequestDto input) {

        Date fechaHoy = DateUtilities.clearTime(DateUtilities.currentDate());

        int citasEmitidas = 0;
        int recetasPorRecoger = 0;

        //List<ConsultarConsExternaDto> consultaExternaList = new ArrayList<>();
        //List<ConsultarRecetaDto> farmaciaList = new ArrayList<>();

        // Obtener datos de Consulta Externa (Citas Emitidas)

        EssiCitaEmitidaRequestDto citasEmitidasReqDto = new EssiCitaEmitidaRequestDto();
        citasEmitidasReqDto.setCodTipDoc(input.getTipoDoc());
        citasEmitidasReqDto.setNumDoc(input.getNumDoc());
        EssiResponseDto<List<EssiCitaEmitidaDto>> essiConsultaCitasEmitidasResp = _essiPaciente.consultaCitasEmitidas(citasEmitidasReqDto);

        if (essiConsultaCitasEmitidasResp.getCodError().equals("0")) {
            if (essiConsultaCitasEmitidasResp.getvDataItem().size() > 0) {

                for (EssiCitaEmitidaDto cita : essiConsultaCitasEmitidasResp.getvDataItem()) {
                    Date fechaCita = DateUtilities.stringDateToDate(cita.getCitFecha());
                    boolean isSameDay = DateUtils.isSameDay(fechaHoy,fechaCita);
                    boolean isValidCentro = true; // (cita.getCitOriCenAsiCod().equals(input.getCodOriCentro()) && cita.getCitCenAsiCod().equals(input.getCodCentro()));

                    if (isSameDay && isValidCentro && cita.isCitaPendiente() && cita.isCitaPresencial()) {
                        citasEmitidas++;
                    }
                }

            }
        }

        //Obtener datos de Consulta Recetas
        Date dFechaIni = new DateTime().minusDays(180).toDate(); //HOY-180 días
        Date dFechaFin = new DateTime().plusDays(180).toDate(); //HOY+180 días

        EssiConsultaRecetaRequestDto essiConsultaRecetaRequestDto = new EssiConsultaRecetaRequestDto();
        essiConsultaRecetaRequestDto.setTipDoc(input.getTipoDoc());
        essiConsultaRecetaRequestDto.setNumDoc(input.getNumDoc());
        essiConsultaRecetaRequestDto.setFecIni(DateUtilities.format(dFechaIni, "dd/MM/yyyy")); //dd/MM/yyyy
        essiConsultaRecetaRequestDto.setFecFin(DateUtilities.format(dFechaFin, "dd/MM/yyyy")); //dd/MM/yyyy
        essiConsultaRecetaRequestDto.setEstado("T"); //T = Todos

        EssiResponseDto<List<EssiConsultaRecetaDto>> essiConsultaRecetaResponse = _essiConsulta.consultaRecetas(essiConsultaRecetaRequestDto);

        if (essiConsultaRecetaResponse.getCodError().equals("0")) {
            if (essiConsultaRecetaResponse.getvDataItem().size() > 0) {

                //Se aplicara filtro de solo recetas por recoger

                //ConsultarRecetaDto obj;
                for (EssiConsultaRecetaDto item : essiConsultaRecetaResponse.getvDataItem()) {
                    if (item.isRecogerAvailable()) {
                        Date fechaEmision = DateUtilities.stringDateToDate(item.getFechaEmision());
                        boolean isValidDate = fechaHoy.compareTo(fechaEmision) <= 0;

                        if (isValidDate) {
                            recetasPorRecoger++;
                        }

                        /*obj = new ConsultarRecetaDto();
                        BeanUtils.copyProperties(item, obj);

                        ConsultarRecetaMedicamentoDto objMedi;
                        List<ConsultarRecetaMedicamentoDto> medicamentos = new ArrayList<>();

                        for (EssiConsultaRecetaMedicamentoDto itemMedi : item.getRecetaMedicametos()) {
                            objMedi = new ConsultarRecetaMedicamentoDto();
                            BeanUtils.copyProperties(itemMedi, objMedi);
                            medicamentos.add(objMedi);
                        }

                        obj.setMedicamentos(medicamentos);
                        farmaciaList.add(obj);*/
                    }
                }
            }
        }

        ConsultarResponseDto data = new ConsultarResponseDto();
        data.setConsultaExterna(citasEmitidas > 0);
        data.setFarmacia(recetasPorRecoger > 0);

        ResponseDto<ConsultarResponseDto> responseDto = new ResponseDto<>();
        responseDto.setData(data);
        return responseDto;
    }

}
