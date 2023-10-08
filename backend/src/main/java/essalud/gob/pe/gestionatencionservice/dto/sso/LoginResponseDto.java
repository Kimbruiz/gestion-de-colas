package essalud.gob.pe.gestionatencionservice.dto.sso;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String id;
    private String accessToken;
    private String refreshToken;
}
