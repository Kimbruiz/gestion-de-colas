package essalud.gob.pe.gestionatencionservice.service;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.TicketAtenderColaRequestDto;

public interface PulsadorService {

    ResponseDto<String> atenderCola(TicketAtenderColaRequestDto input);

}
