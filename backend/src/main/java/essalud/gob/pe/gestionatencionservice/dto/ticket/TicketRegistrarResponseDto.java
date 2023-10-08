package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

import java.util.Date;

@Data
public class TicketRegistrarResponseDto {
    private String codTicket;
    private Date fechaRegistro;
    private String guiidTicket;
    private Long idTicket;
}
