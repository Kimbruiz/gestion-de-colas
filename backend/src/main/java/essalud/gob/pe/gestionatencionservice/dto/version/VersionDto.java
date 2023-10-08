package essalud.gob.pe.gestionatencionservice.dto.version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class VersionDto {
    private String versionLabel;
    private String versionNumber;
    private String versionUrl;
}
