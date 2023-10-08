package essalud.gob.pe.gestionatencionservice.service;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface OficinaService {

    ResponseDto<GetOficinaDto> getOficinaByUsuarioGuiid(String guiidUsuario);

    ResponseDto<Boolean> usuarioRegistrar(OficinaUsuarioRegistrarReqDto input);

    ResponseDto<List<OficinaItemDto>> combo();

    ResponseDto<PageInfo<OficinaListItemDto>> list(PaginationDto input);

    ResponseDto<OficinaListItemDto> get(Long id);

    ResponseDto<Long> register(OficinaRegisterReqDto input);

    ResponseDto<Boolean> update(OficinaUpdateReqDto input);

    ResponseDto<Boolean> delete(Long id);

    ResponseDto<Boolean> updateVideo(MultipartFile videoFile, Long idOficina);

}
