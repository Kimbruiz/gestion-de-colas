package essalud.gob.pe.gestionatencionservice.dto.tv;

import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import lombok.Data;

@Data
public class TvConsultarRequestDto extends PaginationDto {
    private String codOriCentro;
    private String codRed;
    private String codCentro;
}
