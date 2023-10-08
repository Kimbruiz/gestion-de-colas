package essalud.gob.pe.gestionatencionservice.dto.persona;

import essalud.gob.pe.gestionatencionservice.common.constants.TipoBuscarPersona;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Persona;
import lombok.Data;

@Data
public class TicketPersonaDto {
    private boolean found = false;
    private int tipo;
    private String message;
    private Persona persona;
    private Integer tipoRegistro;
}
