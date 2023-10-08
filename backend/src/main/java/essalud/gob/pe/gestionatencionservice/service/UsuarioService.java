package essalud.gob.pe.gestionatencionservice.service;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.UsuarioOficinaDto;

import java.util.List;

public interface UsuarioService {

    ResponseDto<PageInfo<UsuarioOficinaDto>> getUsuariosOficina(PaginationDto input);

    ResponseDto<String> register(RegisterRequestDto input);

}
