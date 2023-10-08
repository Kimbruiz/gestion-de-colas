package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.Data;

@Data
public class EssiConsultaRecetaRequestDto extends EssiGenericRequestDto {
    private String fecIni;
    private String fecFin;
    private String estado;
}
