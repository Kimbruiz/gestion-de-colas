package essalud.gob.pe.gestionatencionservice.dto.operador;

import lombok.Data;

@Data
public class OpTicketEnAtencionDto {
    private String guiid;
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String fecha;
    private String ventanilla;
}
