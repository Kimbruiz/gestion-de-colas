package essalud.gob.pe.gestionatencionservice.dto.auth;

import essalud.gob.pe.gestionatencionservice.dto.oficina.GetOficinaDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.LoginResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.UserDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private LoginResponseDto auth;
    private UserDataDto data;
    private UserInfoDto info;
    private GetOficinaDto oficina;
}
