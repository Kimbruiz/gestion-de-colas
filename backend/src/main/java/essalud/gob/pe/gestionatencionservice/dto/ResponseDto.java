package essalud.gob.pe.gestionatencionservice.dto;

import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ResponseDto<T> {

    private static final long serialVersionUID = 1L;

    private String message;
    private T data;
    private int codResult = ResponseType.SUCCESS;

}