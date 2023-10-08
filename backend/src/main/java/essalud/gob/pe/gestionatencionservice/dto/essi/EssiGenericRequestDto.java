package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssiGenericRequestDto {
    private String tipDoc;
    private String numDoc;
}
