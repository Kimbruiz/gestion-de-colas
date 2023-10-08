package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

@Data
public class TicketFinalizarColaRequestDto {
    private String guiidTicket;
    private String observacion;
    private Integer estado;
}
