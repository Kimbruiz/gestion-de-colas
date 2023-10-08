package essalud.gob.pe.gestionatencionservice.jpa.entity.key;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OficinaUsuarioKey implements Serializable {
    private Long idOficina;
    private String guiid;
}
