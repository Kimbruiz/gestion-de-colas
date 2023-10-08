package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.common.constants.EstadoTicket;
import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.NotifyTicketUpdateRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.TicketAtenderColaRequestDto;
import essalud.gob.pe.gestionatencionservice.service.PulsadorService;
import essalud.gob.pe.gestionatencionservice.service.TicketService;
import essalud.gob.pe.gestionatencionservice.service.WebSocketService;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PulsadorServiceImpl extends BaseService implements PulsadorService {

    private final WebSocketService _webSocketService;
    private final TicketService _ticketService;

    @Override
    public ResponseDto<String> atenderCola(TicketAtenderColaRequestDto input) {
        ResponseDto<String> result = _ticketService.atenderCola(input);

        if (result.getCodResult() == ResponseType.SUCCESS) {
            if (!StringUtilities.isNullOrEmpty(result.getData())) {
                NotifyTicketUpdateRequestDto notifyDto = new NotifyTicketUpdateRequestDto();
                notifyDto.setChannel(input.getCodOficina());
                notifyDto.setGuiidTicket(result.getData());
                notifyDto.setEstado(EstadoTicket.ATENDIDO);
                _webSocketService.notifyTicketUpdate(notifyDto);

                loggerInfo("*** PULSADOR-atenderCola-OK", String.format("(%s-%s): %s", input.getCodOficina(), input.getVentanilla(), result.getData()));
            }
            else {
                loggerInfo("*** PULSADOR-atenderCola-EMPTY", String.format("(%s-%s)", input.getCodOficina(), input.getVentanilla()));
            }
        }
        else {
            loggerError("*** PULSADOR-atenderCola-ERROR", String.format("(%s-%s)", input.getCodOficina(), input.getVentanilla()));
        }

        return result;
    }

}
