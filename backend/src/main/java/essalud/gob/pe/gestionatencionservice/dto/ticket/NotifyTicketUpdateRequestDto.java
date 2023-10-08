package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

@Data
public class NotifyTicketUpdateRequestDto {
    private String channel;
    private String guiidTicket;
    private Integer estado = 0;
}
