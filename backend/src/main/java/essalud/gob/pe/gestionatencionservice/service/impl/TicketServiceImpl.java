package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.client.EssiConsulta;
import essalud.gob.pe.gestionatencionservice.client.EssiPaciente;
import essalud.gob.pe.gestionatencionservice.common.constants.*;
import essalud.gob.pe.gestionatencionservice.components.PersonaComponent;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpTicketEnAtencionDto;
import essalud.gob.pe.gestionatencionservice.dto.persona.TicketPersonaDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.TicketActivoEnAtencionDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.*;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Persona;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Ticket;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.PersonaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.TicketConsultaExternaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.TicketRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.OperadorMyRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.TicketMyRepository;
import essalud.gob.pe.gestionatencionservice.service.TicketService;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import essalud.gob.pe.gestionatencionservice.util.RandomUuidCreator;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository _ticketRepository;
    private final TicketConsultaExternaRepository _ticketConsultaExternaRepository;
    private final EssiPaciente _essiPaciente;
    private final EssiConsulta _essiConsulta;
    private final PersonaComponent _personaComponent;
    private final TicketMyRepository _ticketMyRepository;
    private final PersonaRepository _personaRepository;
    private final OperadorMyRepository _operadorMyRepository;
    private final OficinaRepository _oficinaRepository;

    @SneakyThrows
    @Override
    public ResponseDto<TicketRegistrarGenericResponseDto> registrarConsultaExterna(TicketRegistrarConsultaExternaRequestDto input) {

        /*Date fechaHoy = DateUtilities.clearTime(DateUtilities.currentDate());

        //Obtener datos de cita
        EssiCitaEmitidaRequestDto citasEmitidasReqDto = new EssiCitaEmitidaRequestDto();
        citasEmitidasReqDto.setCodTipDoc(input.getTipoDoc());
        citasEmitidasReqDto.setNumDoc(input.getNumDoc());
        EssiResponseDto<List<EssiCitaEmitidaDto>> essiConsultaCitasEmitidasResp = _essiPaciente.consultaCitasEmitidas(citasEmitidasReqDto);
        EssiCitaEmitidaDto citaElegida = null;

        if (essiConsultaCitasEmitidasResp.getCodError().equals("0")) {
            if (essiConsultaCitasEmitidasResp.getvDataItem().size() > 0) {

                for (EssiCitaEmitidaDto cita : essiConsultaCitasEmitidasResp.getvDataItem()) {
                    Date fechaCita = DateUtilities.stringDateToDate(cita.getCitFecha());
                    boolean isSameDay = DateUtils.isSameDay(fechaHoy,fechaCita);
                    //TODO: se debe habilitar la condicion real
                    boolean isValidCentro = true; // (cita.getCitOriCenAsiCod().equals(input.getCodOriCentro()) && cita.getCitCenAsiCod().equals(input.getCodCentro()));

                    if (isSameDay && isValidCentro && cita.isCitaPendiente() && cita.isCitaPresencial()) {
                        citaElegida = cita;
                        break;
                    }
                }

            }
        }

        ResponseDto<TicketRegistrarGenericResponseDto> responseDto = new ResponseDto<>();

        if (citaElegida != null) {
            TicketRegistrarRequestDto ticketRequest = new TicketRegistrarRequestDto();
            BeanUtils.copyProperties(input, ticketRequest);
            ticketRequest.setTipo(TipoTicket.CONSULTA_EXTERNA); //CONSULTA EXTERNA

            ResponseDto<TicketRegistrarResponseDto> ticketResponse = _registrarTicket(ticketRequest);

            if (ticketResponse.getCodResult() != ResponseType.SUCCESS) {
                throw new ServiceException("No se pudo registrar el ticket de consulta externa. ({0})"
                        .replace("{0}",ticketResponse.getMessage()));
            }

            TicketConsultaExterna ticketConsultaExterna = new TicketConsultaExterna();
            ticketConsultaExterna.setIdTicket(ticketResponse.getData().getIdTicket());
            ticketConsultaExterna.setCodServHosp(citaElegida.getCitServHosCod());
            ticketConsultaExterna.setServHosp(citaElegida.getCitServHosDes());
            ticketConsultaExterna.setProfAsistencial(citaElegida.getCitProfAsis());
            _ticketConsultaExternaRepository.save(ticketConsultaExterna);

            TicketRegistrarGenericResponseDto data = new TicketRegistrarGenericResponseDto();
            data.setCodTicket(ticketResponse.getData().getCodTicket());
            data.setFechaRegistro(ticketResponse.getData().getFechaRegistro());

            responseDto.setData(data);
        }
        else {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se encontro cita valida con fecha actual.");
        }

        return responseDto;*/

        return null;
    }

    @SneakyThrows
    @Override
    public ResponseDto<TicketRegistrarGenericResponseDto> registrarFarmacia(TicketRegistrarFarmaciaRequestDto input) {

        /*Date fechaHoy = DateUtilities.clearTime(DateUtilities.currentDate());

        //Obtener datos de Consulta Recetas
        Date dFechaIni = new DateTime().minusDays(180).toDate(); //HOY-180 días
        Date dFechaFin = new DateTime().plusDays(180).toDate(); //HOY+180 días

        EssiConsultaRecetaRequestDto essiConsultaRecetaRequestDto = new EssiConsultaRecetaRequestDto();
        essiConsultaRecetaRequestDto.setTipDoc(input.getTipoDoc());
        essiConsultaRecetaRequestDto.setNumDoc(input.getNumDoc());
        essiConsultaRecetaRequestDto.setFecIni(DateUtilities.format(dFechaIni, "dd/MM/yyyy")); //dd/MM/yyyy
        essiConsultaRecetaRequestDto.setFecFin(DateUtilities.format(dFechaFin, "dd/MM/yyyy")); //dd/MM/yyyy
        essiConsultaRecetaRequestDto.setEstado("T"); //T = Todos

        int recetasPorRecoger = 0;
        EssiResponseDto<List<EssiConsultaRecetaDto>> essiConsultaRecetaResponse =
                _essiConsulta.consultaRecetas(essiConsultaRecetaRequestDto);

        if (essiConsultaRecetaResponse.getCodError().equals("0")) {
            if (essiConsultaRecetaResponse.getvDataItem().size() > 0) {

                //Se aplicara filtro de solo recetas por recoger
                for (EssiConsultaRecetaDto item : essiConsultaRecetaResponse.getvDataItem()) {
                    if (item.isRecogerAvailable()) {
                        Date fechaEmision = DateUtilities.stringDateToDate(item.getFechaEmision());
                        boolean isValidDate = fechaHoy.compareTo(fechaEmision) <= 0;

                        if (isValidDate) {
                            recetasPorRecoger++;
                        }
                    }
                }
            }
        }

        ResponseDto<TicketRegistrarGenericResponseDto> responseDto = new ResponseDto<>();

        if (recetasPorRecoger > 0) {
            TicketRegistrarRequestDto ticketRequest = new TicketRegistrarRequestDto();
            BeanUtils.copyProperties(input, ticketRequest);
            ticketRequest.setTipo(TipoTicket.FARMACIA); //FARMACIA

            ResponseDto<TicketRegistrarResponseDto> ticketResponse = _registrarTicket(ticketRequest);

            if (ticketResponse.getCodResult() != ResponseType.SUCCESS) {
                throw new ServiceException("No se pudo registrar el ticket de farmacia. ({0})"
                        .replace("{0}",ticketResponse.getMessage()));
            }

            TicketRegistrarGenericResponseDto data = new TicketRegistrarGenericResponseDto();
            data.setCodTicket(ticketResponse.getData().getCodTicket());
            data.setFechaRegistro(ticketResponse.getData().getFechaRegistro());

            responseDto.setData(data);
        }
        else {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se encontraron recetas por recoger con fecha actual.");
        }

        return responseDto;*/

        return null;
    }

    @Override
    public ResponseDto<Long> getAsistentesCount(Long idOficina) {
        ResponseDto<Long> responseDto = new ResponseDto<>();

        Long cant = _ticketMyRepository.getAsistentesCount(idOficina);
        responseDto.setData(cant);
        return responseDto;
    }

    @SneakyThrows
    private ResponseDto<TicketRegistrarResponseDto> _registrarTicket(TicketRegistrarRequestDto input) {

        Date fechaHoy = DateUtilities.currentDate();
        Date fechaHoySinHora = DateUtilities.clearTime(fechaHoy);

        Ticket ticket = new Ticket();
        ticket.setGuiid(RandomUuidCreator.generateSpecial());
        ticket.setCodTicket(StringUtilities.randomString(5).toUpperCase());
        ticket.setCodOriCentro("0"); //TODO: No se usa actualmente
        ticket.setCodRed("00"); //TODO: No se usa actualmente
        ticket.setCodCentro("000"); //TODO: No se usa actualmente
        ticket.setIdPersona(input.getIdPersona());
        ticket.setFechaRegistro(fechaHoy);
        ticket.setTipo(input.getTipo());
        ticket.setIdOficina(input.getIdOficina());
        ticket.setEstado(EstadoTicket.ACTIVO);
        ticket.setNumAten(_ticketRepository.getNextNumAten(DateUtilities.format(fechaHoySinHora, "dd/MM/yyyy"), input.getIdOficina()));
        ticket.setTipoRegistro(input.getTipoRegistro());
        ticket = _ticketRepository.save(ticket);

        TicketRegistrarResponseDto data = new TicketRegistrarResponseDto();
        data.setIdTicket(ticket.getIdTicket());
        data.setCodTicket(ticket.getCodTicket());
        data.setFechaRegistro(ticket.getFechaRegistro());
        data.setGuiidTicket(ticket.getGuiid());

        ResponseDto<TicketRegistrarResponseDto> responseDto = new ResponseDto<>();
        responseDto.setData(data);
        return responseDto;
    }

    private ResponseDto<TicketRegistrarGenericResponseDto> _registrarCola(TicketRegistrarColaRequestDto input, Oficina oficina) {

        ResponseDto<TicketRegistrarGenericResponseDto> responseDto = new ResponseDto<>();

        TicketRegistrarGenericResponseDto data = new TicketRegistrarGenericResponseDto();
        data.setTipoRegistro(input.getTipo());
        data.setTipoRespuesta(TipoRespuestaCola.NORMAL);
        responseDto.setData(data);

        boolean isEmergencia = Objects.equals(oficina.getTipo(), TipoOficina.EMERGENCIA);

        Map<String, Object> params = new HashMap<>();
        params.put("tipoDoc", input.getTipoDoc());
        params.put("numDoc", input.getNumDoc());
        params.put("idOficina", oficina.getIdOficina());

        //Siempre se verifica si hay un ticket en atención actualmente para esa persona
        Optional<TicketActivoEnAtencionDto> ticketEnAtencion = _ticketMyRepository.getTicketActivoEnAtencion(params);
        if (ticketEnAtencion.isPresent()) {

            if (Objects.equals(ticketEnAtencion.get().getEstado(), EstadoTicket.ACTIVO)) {

                if (input.isSobreescribir()) {
                    //En caso tenga un ticket ACTIVO sin atender, se le anulara ese ticket para registrar uno nuevo
                    String guiidTicket = ticketEnAtencion.get().getGuiid();
                    _ticketRepository.updateEstadoByGuiid(EstadoTicket.ANULADO, guiidTicket);
                }
                else {
                    //El SOBREESCRIBIR solo esta disponible para oficinas de EMERGENCIA
                    responseDto.setCodResult(ResponseType.ERROR);
                    if (isEmergencia) {
                        //En caso tenga un ticket ACTIVO sin atender, se le notificara en mensaje de confirmacion para sobreescribir
                        responseDto.setMessage("No se pudo registrar el ticket debido a que ya tiene uno activo. ¿Desea registrarse nuevamente?");

                        data.setTipoRespuesta(TipoRespuestaCola.CONFIRMACION);
                        data.setSobreescribir(true);
                        responseDto.setData(data);
                    }
                    else {
                        responseDto.setMessage("No se pudo registrar el ticket debido a que ya tiene uno activo.");
                    }

                    return responseDto;
                }

            }

        }

        //Verificar si existe la persona. si no existe debe enviar SIN_DATOS (saber si debe guardar, actualizar, etc)

        TicketPersonaDto ticketPersonaDto = null;

        if (Objects.equals(input.getTipo(), TipoRegistroCola.NORMAL)) {
            ticketPersonaDto = _personaComponent.buscarPersona(input.getTipoDoc(),input.getNumDoc(),input.getTipo(),oficina.getTipo());
            if (!ticketPersonaDto.isFound()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage(ticketPersonaDto.getMessage());

                if (!input.isSobreescribir()) {
                    //Si es fallecido no puede entrar a SIN_DATOS
                    if (ticketPersonaDto.getTipo() != TipoBuscarPersona.ERROR) {
                        //El SIN_DATOS indica que se registrara a la persona solo con DNI sin nombres
                        data.setTipoRespuesta(TipoRespuestaCola.CONFIRMACION);
                        data.setTipoRegistro(TipoRegistroCola.SIN_DATOS);
                        responseDto.setData(data);
                    }
                }

                return responseDto;
            }
        }
        else if (Objects.equals(input.getTipo(), TipoRegistroCola.SIN_DATOS)) {
            ticketPersonaDto = _personaComponent.buscarPersona(input.getTipoDoc(),input.getNumDoc(),input.getTipo(),oficina.getTipo());
            if (!ticketPersonaDto.isFound()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage(ticketPersonaDto.getMessage());
                return responseDto;
            }
        }
        if (ticketPersonaDto == null) {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se pudo procesar la información de la persona.");
            return responseDto;
        }

        data.setTipoRegistro(ticketPersonaDto.getTipoRegistro());

        // Registrar / Actualizar los datos de la persona
        boolean updated = _personaComponent.guardarPersona(ticketPersonaDto.getPersona(), ticketPersonaDto.getTipo());

        TicketRegistrarRequestDto ticketRequest = new TicketRegistrarRequestDto();
        ticketRequest.setIdOficina(oficina.getIdOficina());
        ticketRequest.setTipo(TipoTicket.COLA); //COLA
        ticketRequest.setTipoRegistro(data.getTipoRegistro()); //NORMAL / SIN_DATOS / SOBREESCRIBIR
        ticketRequest.setIdPersona(ticketPersonaDto.getPersona().getIdPersona());

        ResponseDto<TicketRegistrarResponseDto> ticketResponse = _registrarTicket(ticketRequest);
        if (ticketResponse.getCodResult() != ResponseType.SUCCESS) {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se pudo registrar el ticket. ({0})".replace("{0}",ticketResponse.getMessage()));
            return responseDto;
        }

        data.setCodTicket(ticketResponse.getData().getCodTicket());
        data.setFechaRegistro(ticketResponse.getData().getFechaRegistro());
        data.setGuiidTicket(ticketResponse.getData().getGuiidTicket());

        responseDto.setData(data);
        responseDto.setCodResult(ResponseType.SUCCESS);
        return responseDto;
    }

    private ResponseDto<TicketRegistrarGenericResponseDto> _registrarColaEvento(TicketRegistrarColaRequestDto input, Oficina oficina) {
        ResponseDto<TicketRegistrarGenericResponseDto> responseDto = new ResponseDto<>();

        TicketRegistrarGenericResponseDto data = new TicketRegistrarGenericResponseDto();
        data.setTipoRegistro(input.getTipo());
        data.setTipoRespuesta(TipoRespuestaCola.NORMAL);
        responseDto.setData(data);

        Map<String, Object> params = new HashMap<>();
        params.put("tipoDoc", input.getTipoDoc());
        params.put("numDoc", input.getNumDoc());
        params.put("idOficina", oficina.getIdOficina());

        //Verificamos si tiene un ticket sin salida
        Optional<TicketActivoEnAtencionDto> ticketActivoSinSalida = _ticketMyRepository.getTicketActivoSinSalida(params);
        boolean tieneTicketActivoSinSalida = ticketActivoSinSalida.isPresent();

        //Verificar si existe la persona. si no existe debe enviar SIN_DATOS (saber si debe guardar, actualizar, etc)
        TicketPersonaDto ticketPersonaDto = null;

        if (Objects.equals(input.getTipo(), TipoRegistroCola.NORMAL)) {
            ticketPersonaDto = _personaComponent.buscarPersona(input.getTipoDoc(),input.getNumDoc(),input.getTipo(),oficina.getTipo());
            if (!ticketPersonaDto.isFound()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage(ticketPersonaDto.getMessage());

                if (!input.isSobreescribir()) {
                    //Si es fallecido no puede entrar a SIN_DATOS
                    if (ticketPersonaDto.getTipo() != TipoBuscarPersona.ERROR) {
                        //El SIN_DATOS indica que se registrara a la persona solo con DNI sin nombres
                        data.setTipoRespuesta(TipoRespuestaCola.CONFIRMACION);
                        data.setTipoRegistro(TipoRegistroCola.SIN_DATOS);
                        responseDto.setData(data);
                    }
                }

                return responseDto;
            }
        }
        else if (Objects.equals(input.getTipo(), TipoRegistroCola.SIN_DATOS)) {
            ticketPersonaDto = _personaComponent.buscarPersona(input.getTipoDoc(),input.getNumDoc(),input.getTipo(),oficina.getTipo());
            if (!ticketPersonaDto.isFound()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage(ticketPersonaDto.getMessage());
                return responseDto;
            }
        }
        if (ticketPersonaDto == null) {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se pudo procesar la información de la persona.");
            return responseDto;
        }

        data.setTipoRegistro(ticketPersonaDto.getTipoRegistro());

        // Registrar / Actualizar los datos de la persona
        boolean updated = _personaComponent.guardarPersona(ticketPersonaDto.getPersona(), ticketPersonaDto.getTipo());

        if (tieneTicketActivoSinSalida) {

            //TODO: debo devolver algo que me pueda permitir identificar en la TV que es una salida - tv socket ?

            //SI Tiene ticket activo sin salida marcada, entonces marcamos salida
            String guiidTicket = ticketActivoSinSalida.get().getGuiid();
            _ticketRepository.updateFechaSalidaByGuiid(guiidTicket);

            //data.setCodTicket(ticketResponse.getData().getCodTicket());
            //data.setFechaRegistro(ticketResponse.getData().getFechaRegistro());
            data.setGuiidTicket(guiidTicket);

            responseDto.setMessage("SALIDA registrada correctamente.");
        }
        else {
            //NO Tiene ticket activo sin salida marcada, entonces registramos entrada

            TicketRegistrarRequestDto ticketRequest = new TicketRegistrarRequestDto();
            ticketRequest.setIdOficina(oficina.getIdOficina());
            ticketRequest.setTipo(TipoTicket.COLA); //COLA
            ticketRequest.setTipoRegistro(data.getTipoRegistro()); //NORMAL / SIN_DATOS / SOBREESCRIBIR
            ticketRequest.setIdPersona(ticketPersonaDto.getPersona().getIdPersona());

            ResponseDto<TicketRegistrarResponseDto> ticketResponse = _registrarTicket(ticketRequest);
            if (ticketResponse.getCodResult() != ResponseType.SUCCESS) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage("No se pudo registrar el ticket. ({0})".replace("{0}",ticketResponse.getMessage()));
                return responseDto;
            }

            data.setCodTicket(ticketResponse.getData().getCodTicket());
            data.setFechaRegistro(ticketResponse.getData().getFechaRegistro());
            data.setGuiidTicket(ticketResponse.getData().getGuiidTicket());

            responseDto.setMessage("ENTRADA registrada correctamente.");
        }

        responseDto.setData(data);
        responseDto.setCodResult(ResponseType.SUCCESS);
        return responseDto;
    }

    @Transactional
    @Override
    public ResponseDto<TicketRegistrarGenericResponseDto> registrarCola(TicketRegistrarColaRequestDto input) {

        ResponseDto<TicketRegistrarGenericResponseDto> responseDto = new ResponseDto<>();

        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            responseDto.setCodResult(ResponseType.ERROR);
            responseDto.setMessage("No se encontro información de la oficina.");
            return responseDto;
        }

        boolean isEvento = Objects.equals(oficina.get().getTipo(), TipoOficina.EVENTO);
        if (isEvento) {
            responseDto = _registrarColaEvento(input, oficina.get());
        }
        else {
            responseDto = _registrarCola(input, oficina.get());
        }

        return responseDto;
    }

    private ResponseDto<String> _atenderTicket(
            String guiidTicket,
            String guiidOperador,
            Long idOficina,
            Integer tipoOficina,
            String ventanilla
    ) {

        Map<String, Object> params = new HashMap<>();
        params.put("guiidOperador", guiidOperador);
        params.put("idOficina", idOficina);

        String tipo = (Objects.equals(tipoOficina, TipoOficina.OSPE)) ? "en la ventanilla" : "en triaje";

        Optional<OpTicketEnAtencionDto> ticketEnAtencion = _operadorMyRepository.getTicketEnAtencion(params);
        if (ticketEnAtencion.isPresent()) {
            throw new ServiceException("Usted ya esta atendiendo a un usuario de nombre: {0}, {1}: {2}."
                    .replace("{0}", ticketEnAtencion.get().getNombres())
                    .replace("{1}", tipo)
                    .replace("{2}", ticketEnAtencion.get().getVentanilla()));
        }

        Optional<Ticket> ticket = _ticketRepository.findFirstByGuiid(guiidTicket);
        if (ticket.isEmpty()) {
            throw new ServiceException("No se encontro información del ticket a atender ({0})."
                    .replace("{0}", guiidTicket));
        }

        Date fechaHoy = DateUtilities.currentDate();

        ticket.get().setGuiidOperador(guiidOperador);
        ticket.get().setFechaAtendido(fechaHoy);
        ticket.get().setVentanilla(ventanilla);
        ticket.get().setEstado(EstadoTicket.ATENDIDO);
        _ticketRepository.save(ticket.get());

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setData(ticket.get().getGuiid());
        return responseDto;
    }

    @Override
    public ResponseDto<String> atenderCola(TicketAtenderColaRequestDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("idOficina", oficina.get().getIdOficina());
        Optional<String> nextTicketGuiid = _ticketMyRepository.getSiguienteTicket(params);
        if (nextTicketGuiid.isEmpty()) {
            throw new ServiceException("No se encontro otro ticket disponible para atender.");
        }

        return _atenderTicket(
            nextTicketGuiid.get(),
            input.getGuiidOperador(),
            oficina.get().getIdOficina(),
            oficina.get().getTipo(),
            input.getVentanilla()
        );
    }

    @Override
    public ResponseDto<String> atenderColaEspecifica(TicketAtenderColaEspRequestDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        return _atenderTicket(
            input.getGuiidTicket(),
            input.getGuiidOperador(),
            oficina.get().getIdOficina(),
            oficina.get().getTipo(),
            input.getVentanilla()
        );
    }

    @Override
    public ResponseDto<Boolean> finalizarCola(TicketFinalizarColaRequestDto input) {
        if (!(input.getEstado().equals(EstadoTicket.FINALIZADO) || input.getEstado().equals(EstadoTicket.NO_SE_PRESENTO))) {
            throw new ServiceException("El estado indicado no es valido.");
        }

        Optional<Ticket> ticket = _ticketRepository.findFirstByGuiid(input.getGuiidTicket());
        if (ticket.isEmpty()) {
            throw new ServiceException("No se encontro información del ticket a finalizar.");
        }

        Date fechaHoy = DateUtilities.currentDate();

        ticket.get().setFechaFinalizado(fechaHoy);
        ticket.get().setObservacion(input.getObservacion());
        ticket.get().setEstado(input.getEstado());
        _ticketRepository.save(ticket.get());

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

    @Override
    public ResponseDto<Boolean> anularCola(TicketAnularColaRequestDto input) {
        Optional<Ticket> ticket = _ticketRepository.findFirstByGuiid(input.getGuiidTicket());
        if (ticket.isEmpty()) {
            throw new ServiceException("No se encontro información del ticket a anular.");
        }

        Date fechaHoy = DateUtilities.currentDate();

        ticket.get().setFechaFinalizado(fechaHoy);
        ticket.get().setObservacion(input.getObservacion());
        ticket.get().setEstado(EstadoTicket.ANULADO);
        _ticketRepository.save(ticket.get());

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

    @Override
    public ResponseDto<ValidarTicketResponseDto> validarTicket(NotifyTicketUpdateRequestDto input) {
        ResponseDto<ValidarTicketResponseDto> responseDto = new ResponseDto<>();

        //SI SE CUMPLE ESTA CONDICION QUIERE DECIR QUE SE VA A REALIZAR LA LLAMADA DE LA PERSONA
        boolean isAtendido = input.getEstado().equals(EstadoTicket.ATENDIDO) && !StringUtilities.isNullOrEmpty(input.getGuiidTicket());

        if (isAtendido) {

            Optional<Ticket> ticket = _ticketRepository.findFirstByGuiid(input.getGuiidTicket());
            if (ticket.isEmpty()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage("No se encontro información del ticket.");
                return responseDto;
            }

            Long idPersona = ticket.get().getIdPersona();
            Optional<Persona> persona = _personaRepository.findById(idPersona);
            if (persona.isEmpty()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage("No se encontro información de la persona o sus datos no son validos ({0}).".replace("{0}", String.valueOf(idPersona)));
                return responseDto;
            }

            Optional<Oficina> oficina = _oficinaRepository.findFirstById(ticket.get().getIdOficina());
            if (oficina.isEmpty()) {
                responseDto.setCodResult(ResponseType.ERROR);
                responseDto.setMessage(String.format("No se encontro información de la oficina %s", ticket.get().getIdOficina()));
                return responseDto;
            }

            if (Objects.equals(oficina.get().getTipo(), TipoOficina.OSPE) || Objects.equals(oficina.get().getTipo(), TipoOficina.EMERGENCIA)) {

                String ventanilla = StringUtilities.isNullOrEmpty(ticket.get().getVentanilla()) ? "0" : ticket.get().getVentanilla();
                boolean isEmergencia = Objects.equals(oficina.get().getTipo(), TipoOficina.EMERGENCIA);
                String speechBase = _getSpeechBase(isEmergencia, ticket.get().getTipoRegistro(), oficina.get().getTipo());

                String speech = ""; //El Speech que pronunciara al llamar a un usuario
                String usuario = ""; //El valor que sera visible en la grilla de la TV

                if (Objects.equals(ticket.get().getTipoRegistro(), TipoRegistroCola.NORMAL)) {

                    String genero = (persona.get().getGenero().equals("M")) ? "Señor" : "Señorita";
                    String nombres = persona.get().getPriNombre() + " " + persona.get().getApePaterno();

                    speech = speechBase
                            .replace("{genero}", genero)
                            .replace("{nombres}", nombres)
                            .replace("{ventanilla}", ventanilla);

                    usuario = persona.get().getPriNombre() + " " + persona.get().getApePaterno().substring(0,1) + ".";
                }
                else if (Objects.equals(ticket.get().getTipoRegistro(), TipoRegistroCola.SIN_DATOS)) {
                    String numAten = StringUtilities.numberToText(ticket.get().getNumAten());

                    if (Objects.equals(oficina.get().getTipo(), TipoOficina.OSPE)) {
                        speech = speechBase
                                .replace("{numDoc}", persona.get().getNumDoc())
                                .replace("{ventanilla}", ventanilla);

                        usuario = persona.get().getNumDoc();
                    }
                    else if (Objects.equals(oficina.get().getTipo(), TipoOficina.EMERGENCIA)) {
                        speech = speechBase
                                .replace("{numAten}", numAten)
                                .replace("{ventanilla}", ventanilla);

                        usuario = "#" + ticket.get().getNumAten().toString();
                    }
                }

                ValidarTicketResponseDto data = new ValidarTicketResponseDto();
                data.setSpeech(speech);
                data.setNombres(usuario);
                data.setVentanilla(ventanilla);
                responseDto.setData(data);

            }
            else if (Objects.equals(oficina.get().getTipo(), TipoOficina.EVENTO)) {

                String speech = ""; //El Speech que pronunciara al llamar a un usuario
                String usuario = ""; //El valor que sera visible en la grilla de la TV

                if (Objects.equals(ticket.get().getTipoRegistro(), TipoRegistroCola.NORMAL)) {
                    String genero = (persona.get().getGenero().equals("M")) ? "Señor" : "Señorita";
                    String generoBienv = (persona.get().getGenero().equals("M")) ? "Bienvenido" : "Bienvenida";

                    //ES SALIDA ?
                    if (ticket.get().getFechaSalida() != null) {
                        generoBienv = "Hasta Pronto";
                    }

                    String nombres = persona.get().getPriNombre() + " " + persona.get().getApePaterno();

                    speech = "{generoBienv} {genero} {nombres}"
                            .replace("{generoBienv}", generoBienv)
                            .replace("{genero}", genero)
                            .replace("{nombres}", nombres);

                    usuario = generoBienv + " " + persona.get().getPriNombre() + " " + persona.get().getApePaterno().substring(0,1) + ".";
                }
                else if (Objects.equals(ticket.get().getTipoRegistro(), TipoRegistroCola.SIN_DATOS)) {
                    String generoBienv = "Bienvenido";

                    //ES SALIDA ?
                    if (ticket.get().getFechaSalida() != null) {
                        generoBienv = "Hasta Pronto";
                    }

                    speech = "{generoBienv} Número de documento {numDoc}"
                            .replace("{generoBienv}", generoBienv)
                            .replace("{numDoc}", persona.get().getNumDoc());

                    usuario = generoBienv + " " + persona.get().getNumDoc();
                }

                ValidarTicketResponseDto data = new ValidarTicketResponseDto();
                data.setSpeech(speech);
                data.setNombres(usuario);
                data.setVentanilla(persona.get().getNumDoc());
                responseDto.setData(data);
            }
        }

        return responseDto;
    }

    private String _getSpeechBase(boolean isEmergencia, int tipoRegistro, int tipoOficina) {
        String speechSection = "";

        if (tipoRegistro == TipoRegistroCola.NORMAL) {
            speechSection = "{genero} {nombres}";
        }
        else if (tipoRegistro == TipoRegistroCola.SIN_DATOS) {

            if (tipoOficina == TipoOficina.OSPE) {
                speechSection = "Número de documento {numDoc}";
            }
            else if (tipoOficina == TipoOficina.EMERGENCIA) {
                speechSection = "Número de atención {numAten}";
            }

        }

        String speechBase = "{speechSection}, por favor acercarse a la ventanilla numero {ventanilla}";

        if (isEmergencia) {
            speechBase = "{speechSection}, por favor acercarse a triaje numero {ventanilla}";
        }

        speechBase = speechBase.replace("{speechSection}", speechSection);
        return speechBase;
    }

}
