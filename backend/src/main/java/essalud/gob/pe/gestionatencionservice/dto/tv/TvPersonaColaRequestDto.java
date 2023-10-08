package essalud.gob.pe.gestionatencionservice.dto.tv;

import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import lombok.Data;

@Data
public class TvPersonaColaRequestDto extends PaginationDto {
    private String codOficina;
}
