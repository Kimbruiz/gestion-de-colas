package essalud.gob.pe.gestionatencionservice.dto;

import lombok.Data;

@Data
public class AppGenericRequestDto {
    private String codOriCentro;
    private String codRed;
    private String codCentro;

    private String tipoDoc;
    private String numDoc;
}
