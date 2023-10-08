package essalud.gob.pe.gestionatencionservice.dto.tv;

import lombok.Data;

@Data
public class TvConsultaExternaDto {
    private String codTicket;
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String servHosp;
}
