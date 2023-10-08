package essalud.gob.pe.gestionatencionservice.dto.ticket;

import essalud.gob.pe.gestionatencionservice.dto.AppGenericOspeRequestDto;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Persona;
import lombok.Data;

@Data
public class TicketRegistrarRequestDto {
    private Long idOficina;
    private Integer tipo; //Tipo de Cola
    private Integer tipoRegistro;
    private Long idPersona;
}
