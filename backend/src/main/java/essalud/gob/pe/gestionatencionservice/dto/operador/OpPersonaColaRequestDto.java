package essalud.gob.pe.gestionatencionservice.dto.operador;

import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import lombok.Data;

@Data
public class OpPersonaColaRequestDto extends PaginationDto {
    private String guiidOperador;
    private String codOficina;
}
