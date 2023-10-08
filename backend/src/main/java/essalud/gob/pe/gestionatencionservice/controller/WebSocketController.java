package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketPrintRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketPrintResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketUpdateRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.ValidarTicketResponseDto;
import essalud.gob.pe.gestionatencionservice.service.TicketService;
import essalud.gob.pe.gestionatencionservice.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate _simpMessagingTemplate;
    private final TicketService _ticketService;
    private final WebSocketService _webSocketService;

    @MessageMapping("/ticket/notify-update")
    public void notifyTicketUpdate(@RequestBody NotifyTicketUpdateRequestDto input) {
        //ResponseDto<ValidarTicketResponseDto> responseDto = _ticketService.validarTicket(input);
        //_simpMessagingTemplate.convertAndSend("/topic/on-ticket-updated/" + input.getChannel(), responseDto);
        _webSocketService.notifyTicketUpdate(input);
    }

    @MessageMapping("/ticket/notify-print")
    public void notifyTicketPrint(@RequestBody NotifyTicketPrintRequestDto input) {
        //NotifyTicketPrintResponseDto responseDto = new NotifyTicketPrintResponseDto();
        //responseDto.setGuiidTicket(input.getGuiidTicket());
        //_simpMessagingTemplate.convertAndSend("/topic/on-ticket-print/" + input.getChannel(), responseDto);
        _webSocketService.notifyTicketPrint(input);
    }

    //@SendTo("/topic/ticket-updated")

}
