package essalud.gob.pe.gestionatencionservice.dto.ideapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdeapiResponseDto<T> implements Serializable {
    private String mensaje;
    private String status;
    private String timestamp;
    private T data;
}
