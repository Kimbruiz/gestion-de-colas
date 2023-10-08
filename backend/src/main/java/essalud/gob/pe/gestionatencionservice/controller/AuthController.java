package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.auth.LoginDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.LoginRequestDto;
import essalud.gob.pe.gestionatencionservice.service.SsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final SsoService _ssoService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto input) {
        ResponseDto<LoginDto> result = _ssoService.login(input);
        return ResponseEntity.ok(result);
    }

}
