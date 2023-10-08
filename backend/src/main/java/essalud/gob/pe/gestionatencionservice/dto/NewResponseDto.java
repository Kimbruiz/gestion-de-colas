package essalud.gob.pe.gestionatencionservice.dto;

import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewResponseDto<T> {
    private static final long serialVersionUID = 1L;

    private int code = ResponseType.SUCCESS;
    private String message;
    private T data;
}