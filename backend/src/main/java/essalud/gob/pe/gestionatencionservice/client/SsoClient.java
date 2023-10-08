package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.common.constants.Constantes;
import essalud.gob.pe.gestionatencionservice.dto.sso.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ssoclientservice", url = "${feign-clients.sso.url}")
public interface SsoClient {

    String APP_KEY = "?g=${" + Constantes.SSO_APP_KEY + "}";

    @PostMapping("auth/login" + APP_KEY)
    LoginResponseDto login(@RequestBody LoginRequestDto input);

    @PostMapping("usuario/getUserInfo" + APP_KEY)
    UserDataDto getUserInfo(@RequestHeader("Authorization") String bearerToken, @RequestBody GetUserInfoReqDto input);

    @PostMapping("auth/register-instant" + APP_KEY)
    String registerInstant(@RequestBody RegisterInstantReqDto input);

}
