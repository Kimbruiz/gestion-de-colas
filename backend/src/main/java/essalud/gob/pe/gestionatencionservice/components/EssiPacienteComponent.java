package essalud.gob.pe.gestionatencionservice.components;

import essalud.gob.pe.gestionatencionservice.client.EssiPaciente;
import essalud.gob.pe.gestionatencionservice.client.UtilServiceClient;
import essalud.gob.pe.gestionatencionservice.common.constants.EssiCodError;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoRegistroCola;
import essalud.gob.pe.gestionatencionservice.dto.essi.EssiGetPacienteRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.essi.EssiPacienteDto;
import essalud.gob.pe.gestionatencionservice.dto.essi.EssiResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaDatosDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaTicket;
import essalud.gob.pe.gestionatencionservice.dto.util.CentroDto;
import essalud.gob.pe.gestionatencionservice.my_repository.OperadorMyRepository;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EssiPacienteComponent {

    private final EssiPaciente _essiPaciente;
    private final OperadorMyRepository _operadorMyRepository;
    private final UtilServiceClient _utilServiceClient;

    public Optional<EssiPacienteDto> buscar(EssiGetPacienteRequestDto input) {
        Optional<EssiPacienteDto> result = Optional.empty();

        EssiResponseDto<List<EssiPacienteDto>> essiPacienteResponse = _essiPaciente.getPacienteEssi(input);
        if (essiPacienteResponse.getCodError().equals(EssiCodError.SUCCESS)) {
            if (essiPacienteResponse.getvDataItem() != null) {
                if (essiPacienteResponse.getvDataItem().size() > 0) {
                    EssiPacienteDto essiPacienteDto = essiPacienteResponse.getvDataItem().get(0);
                    result = Optional.of(essiPacienteDto);
                }
            }
        }
        else {
            if (!StringUtilities.isNullOrEmpty(essiPacienteResponse.getDesError())) {
                throw new ServiceException(String.format("ESSI: %s", essiPacienteResponse.getDesError()));
            }
        }

        return result;
    }

    public OpPersonaDatosDto getPersonaDatosByTicket(String guiidTicket) {
        Optional<OpPersonaTicket> personaTicket = _operadorMyRepository.getPersonaTicket(guiidTicket);
        if (personaTicket.isEmpty()) {
            throw new ServiceException("No se encontro información de la persona.");
        }

        var essiPacienteRequest = EssiGetPacienteRequestDto.builder()
                .codOpcion("1") //TODOS
                .codTipDoc(personaTicket.get().getTipoDoc())
                .numDoc(personaTicket.get().getNumDoc())
                .fecNacimiento(personaTicket.get().getFecNacimiento())
                .build();

        String red = ""; //NO ENCONTRADO
        String centro = ""; //NO ENCONTRADO
        String fecVigHasta = "";

        if (Objects.equals(personaTicket.get().getTipoRegistro(), TipoRegistroCola.NORMAL)) {

            Optional<EssiPacienteDto> essiPaciente = this.buscar(essiPacienteRequest);
            if (essiPaciente.isEmpty()) {
                throw new ServiceException("No se encontro información del paciente en ESSI.");
            }

            fecVigHasta = essiPaciente.get().getFecVigHasta();

            CentroDto centroDto = _utilServiceClient.getCentro(essiPaciente.get().getCodCentro());
            if (centroDto != null) {
                red = centroDto.getDesRed();
                centro = centroDto.getNomCentro();
            }

        }

        return OpPersonaDatosDto.builder()
            .tipoDoc(personaTicket.get().getTipoDoc())
            .numDoc(personaTicket.get().getNumDoc())
            .nombres(StringUtilities.ifNull(personaTicket.get().getNombres(),""))
            .apellidos(StringUtilities.ifNull(personaTicket.get().getApellidos(),""))
            .fecNac(StringUtilities.ifNull(personaTicket.get().getFecNacimiento(),""))
            .edad(StringUtilities.ifNull(personaTicket.get().getEdad(),""))
            .red(StringUtilities.ifNull(red,""))
            .ipress(StringUtilities.ifNull(centro,""))
            .vigenciaHasta(StringUtilities.ifNull(fecVigHasta,""))
            .fecha(personaTicket.get().getFecha())
            .hora(personaTicket.get().getHora())
            .oficina(personaTicket.get().getOficina())
            .numAten(personaTicket.get().getNumAten())
            .build();
    }

}
