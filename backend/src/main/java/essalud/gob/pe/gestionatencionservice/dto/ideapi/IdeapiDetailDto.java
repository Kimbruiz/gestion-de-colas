package essalud.gob.pe.gestionatencionservice.dto.ideapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class IdeapiDetailDto {
    private boolean fallecido;
    private boolean mayorEdad;
    private String priNombre;
    private String segNombre;
    private String apePaterno;
    private String apeMaterno;
    private String sexo;
    private Date fecNac;
    private String fotoBase64;
}
