package essalud.gob.pe.gestionatencionservice.service;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.auth.LoginDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.LoginRequestDto;

public interface SsoService {

    ResponseDto<LoginDto> login(LoginRequestDto input);

}
