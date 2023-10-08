package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

@Data
public class TicketAtenderColaRequestDto {
    private String guiidOperador;
    private String ventanilla;
    private String codOficina;
}
