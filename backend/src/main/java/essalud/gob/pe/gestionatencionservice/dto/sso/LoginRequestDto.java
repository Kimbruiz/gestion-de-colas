package essalud.gob.pe.gestionatencionservice.dto.sso;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username; //6-20
    private String password;
}
