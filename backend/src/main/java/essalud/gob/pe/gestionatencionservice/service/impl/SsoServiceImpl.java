package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.client.SsoClient;
import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoTicket;
import essalud.gob.pe.gestionatencionservice.common.constants.UsuarioRol;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.auth.LoginDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.auth.UserInfoDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.GetOficinaDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.*;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.entity.OficinaUsuario;
import essalud.gob.pe.gestionatencionservice.jpa.entity.UserInfo;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaUsuarioRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.UserInfoRepository;
import essalud.gob.pe.gestionatencionservice.service.OficinaService;
import essalud.gob.pe.gestionatencionservice.service.SsoService;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SsoServiceImpl extends BaseService implements SsoService {

    private final SsoClient _ssoClient;
    private final OficinaService _oficinaService;

    @Override
    public ResponseDto<LoginDto> login(LoginRequestDto input) {
        LoginResponseDto loginResponseDto = _ssoClient.login(input);

        String token = "Bearer {0}".replace("{0}", loginResponseDto.getAccessToken());
        UserDataDto userDataDto = _ssoClient.getUserInfo(token, new GetUserInfoReqDto(loginResponseDto.getId()));

        Set<String> rolCodes = new HashSet<>(Arrays.asList(UsuarioRol.ADMIN, UsuarioRol.ADMINOFI, UsuarioRol.TOTEM, UsuarioRol.TV, UsuarioRol.OPERADOR));
        boolean hasAnyValidRol = userDataDto.getRoles().stream()
                .anyMatch(rol -> rolCodes.contains(rol.getCodigo()));

        if (!hasAnyValidRol) {
            throw new ServiceException("El usuario no tiene un rol valido.");
        }

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setCodOriCentro("0");
        userInfoDto.setCodRed("00");
        userInfoDto.setCodCentro("000");
        userInfoDto.setTipo(TipoTicket.COLA);

        LoginDto loginDto  = new LoginDto();

        boolean isAdmin = userDataDto.getRoles().stream().anyMatch(rol -> UsuarioRol.ADMIN.equals(rol.getCodigo()));

        //Si es Admin no va a requerir oficina
        if (!isAdmin) {
            ResponseDto<GetOficinaDto> oficinaDto = _oficinaService.getOficinaByUsuarioGuiid(loginResponseDto.getId());
            if (oficinaDto.getData() == null) {
                throw new ServiceException("No se encontro información de oficina del usuario");
            }

            loginDto.setOficina(oficinaDto.getData());
        }

        loginDto.setAuth(loginResponseDto);
        loginDto.setData(userDataDto);
        loginDto.setInfo(userInfoDto);

        ResponseDto<LoginDto> response = new ResponseDto<>();
        response.setData(loginDto);
        return response;
    }

}
