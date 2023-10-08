package essalud.gob.pe.gestionatencionservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.client.SsoClient;
import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.sso.RegisterInstantReqDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.UsuarioOficinaDto;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.entity.OficinaUsuario;
import essalud.gob.pe.gestionatencionservice.jpa.entity.UserInfo;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaUsuarioRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.UserInfoRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.OficinaMyRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.UsuarioMyRepository;
import essalud.gob.pe.gestionatencionservice.service.UsuarioService;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import essalud.gob.pe.gestionatencionservice.util.JsonUtilities;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends BaseService implements UsuarioService {

    private final UsuarioMyRepository usuarioMyRepository;
    private final OficinaRepository _oficinaRepository;
    private final OficinaUsuarioRepository _oficinaUsuarioRepository;
    private final UserInfoRepository _userInfoRepository;
    private final SsoClient _ssoClient;
    private final OficinaMyRepository _oficinaMyRepository;

    @Value("${sso-admin-key}")
    private String ssoAdminKey;

    @Override
    public ResponseDto<PageInfo<UsuarioOficinaDto>> getUsuariosOficina(PaginationDto input) {
        PageHelper.startPage(input.getPageNum(), input.getPageSize());

        Map<String, Object> params = new HashMap<>();
        params.put("filter", input.getFilter());
        params.put("id", input.getId());

        List<UsuarioOficinaDto> list = usuarioMyRepository.getUsuariosOficina(params);
        PageInfo<UsuarioOficinaDto> pageInfo = new PageInfo<>(list);

        ResponseDto<PageInfo<UsuarioOficinaDto>> responseDto = new ResponseDto<>();
        responseDto.setData(pageInfo);
        return responseDto;
    }

    @Override
    public ResponseDto<String> register(RegisterRequestDto input) {
        //TODO: cuando tenga token se debera validar las limitaciones (ADMINOFI: idOficina y rol segun donde esta conectado)

        Optional<Oficina> oficina = _oficinaRepository.findFirstById(input.getIdOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        //validar si ya existe registro del usuario en 'oficina-usuario'
        Long oficinasExistentes = _oficinaMyRepository.getOficinasCountByUsuario(input.getUsuario());
        if (oficinasExistentes > 0L) {
            throw new ServiceException("El usuario ya se encuentra registrado en una oficina.");
        }

        Date fechaHoy = DateUtilities.currentDate();

        RegisterInstantReqDto registerInstantReqDto = new RegisterInstantReqDto();
        BeanUtils.copyProperties(input, registerInstantReqDto);
        registerInstantReqDto.setFechaInicioVigencia(DateUtilities.format(fechaHoy, "dd/MM/yyyy"));
        registerInstantReqDto.setKey(ssoAdminKey);
        registerInstantReqDto.setGuiidAdmin(input.getGuiidAdmin());
        registerInstantReqDto.setObservacion("GESTION_ATENCION");
        String guiid = _ssoClient.registerInstant(registerInstantReqDto);

        if (StringUtilities.isNullOrEmpty(guiid)) {
            throw new ServiceException("No se pudo registrar en el SSO");
        }

        if (guiid.contains("codResult")) {
            ResponseDto<?> responseSso = JsonUtilities.jsonStringToObject(guiid, ResponseDto.class);
            if (responseSso != null) {
                if (responseSso.getCodResult() != ResponseType.SUCCESS) {
                    throw new ServiceException(responseSso.getMessage());
                }
            }
        }

        //Registrar en el viva-essalud-service ? (se tendria que exponer metodo para registrar a su tabla)

        OficinaUsuario oficinaUsuario = new OficinaUsuario();
        oficinaUsuario.setIdOficina(oficina.get().getIdOficina());
        oficinaUsuario.setGuiid(guiid);
        oficinaUsuario.setUsuario(input.getUsuario());
        oficinaUsuario.setFechaRegistro(fechaHoy);
        oficinaUsuario.setEstado(1);
        oficinaUsuario.setNombres(input.getNombres());
        _oficinaUsuarioRepository.save(oficinaUsuario);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setData(guiid);
        return responseDto;
    }

}
