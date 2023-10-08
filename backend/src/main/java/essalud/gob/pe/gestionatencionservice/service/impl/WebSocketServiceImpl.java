package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketPrintRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketPrintResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketUpdateRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.ValidarTicketResponseDto;
import essalud.gob.pe.gestionatencionservice.service.TicketService;
import essalud.gob.pe.gestionatencionservice.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate _simpMessagingTemplate;
    private final TicketService _ticketService;

    @Override
    public void notifyTicketUpdate(NotifyTicketUpdateRequestDto input) {
        ResponseDto<ValidarTicketResponseDto> responseDto = _ticketService.validarTicket(input);
        _simpMessagingTemplate.convertAndSend("/topic/on-ticket-updated/" + input.getChannel(), responseDto);
    }

    @Override
    public void notifyTicketPrint(NotifyTicketPrintRequestDto input) {
        NotifyTicketPrintResponseDto responseDto = new NotifyTicketPrintResponseDto();
        responseDto.setGuiidTicket(input.getGuiidTicket());
        _simpMessagingTemplate.convertAndSend("/topic/on-ticket-print/" + input.getChannel(), responseDto);
    }

}
