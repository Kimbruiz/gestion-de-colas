package essalud.gob.pe.gestionatencionservice.service;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.*;

public interface TicketService {

    ResponseDto<TicketRegistrarGenericResponseDto> registrarConsultaExterna(TicketRegistrarConsultaExternaRequestDto input);
    ResponseDto<TicketRegistrarGenericResponseDto> registrarFarmacia(TicketRegistrarFarmaciaRequestDto input);

    ResponseDto<Long> getAsistentesCount(Long idOficina);
    ResponseDto<TicketRegistrarGenericResponseDto> registrarCola(TicketRegistrarColaRequestDto input);
    ResponseDto<String> atenderCola(TicketAtenderColaRequestDto input);
    ResponseDto<String> atenderColaEspecifica(TicketAtenderColaEspRequestDto input);
    ResponseDto<Boolean> finalizarCola(TicketFinalizarColaRequestDto input);
    ResponseDto<Boolean> anularCola(TicketAnularColaRequestDto input);

    ResponseDto<ValidarTicketResponseDto> validarTicket(NotifyTicketUpdateRequestDto input);

}
