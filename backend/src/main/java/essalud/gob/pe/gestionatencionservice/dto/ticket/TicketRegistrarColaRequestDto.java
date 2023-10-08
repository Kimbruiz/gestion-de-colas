package essalud.gob.pe.gestionatencionservice.dto.ticket;

import essalud.gob.pe.gestionatencionservice.dto.AppGenericOspeRequestDto;
import lombok.Data;

@Data
public class TicketRegistrarColaRequestDto extends AppGenericOspeRequestDto {
    private Integer tipo; //tipoRegistro
    private boolean sobreescribir;
}
