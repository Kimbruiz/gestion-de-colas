package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

@Data
public class TicketAtenderColaEspRequestDto {
    private String guiidTicket;
    private String guiidOperador;
    private String ventanilla;
    private String codOficina;
}
