package essalud.gob.pe.gestionatencionservice.service;

import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketPrintRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketUpdateRequestDto;

public interface WebSocketService {

    void notifyTicketUpdate(NotifyTicketUpdateRequestDto input);

    void notifyTicketPrint(NotifyTicketPrintRequestDto input);

}
