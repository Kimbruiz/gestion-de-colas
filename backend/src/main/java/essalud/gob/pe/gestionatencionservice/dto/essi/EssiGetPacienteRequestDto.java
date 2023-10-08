package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class EssiGetPacienteRequestDto {
    private String codOpcion;
    private String codTipDoc;
    private String numDoc;
    private String fecNacimiento;
}
