package essalud.gob.pe.gestionatencionservice.dto.ideapi;

import java.util.Date;

public interface IdeapiDetail {

    boolean isFallecido();

    boolean isMayorEdad();

    String getPriNombre();

    String getSegNombre();

    String getApePaterno();

    String getApeMaterno();

    String getSexo();

    Date getFecNac();

    String getFoto();

}
