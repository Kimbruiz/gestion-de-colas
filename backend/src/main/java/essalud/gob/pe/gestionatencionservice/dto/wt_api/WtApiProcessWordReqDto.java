package essalud.gob.pe.gestionatencionservice.dto.wt_api;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class WtApiProcessWordReqDto {
    private String name;
    private Map<String, String> data;
}
