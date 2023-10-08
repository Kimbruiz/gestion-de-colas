package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

@Data
public class TicketActivoEnAtencionDto {
    private String guiid;
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String fecha;
    private String ventanilla;
    private Integer estado;
}
