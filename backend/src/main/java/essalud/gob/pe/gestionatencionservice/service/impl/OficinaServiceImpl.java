package essalud.gob.pe.gestionatencionservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoOficina;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.*;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.entity.OficinaUsuario;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaUsuarioRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.OficinaMyRepository;
import essalud.gob.pe.gestionatencionservice.service.OficinaService;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OficinaServiceImpl implements OficinaService {

    private final OficinaRepository _oficinaRepository;
    private final OficinaMyRepository _oficinaMyRepository;
    private final OficinaUsuarioRepository _oficinaUsuarioRepository;

    @Value("${tv-video-folder}")
    private String tvVideoFolder;

    @Override
    public ResponseDto<GetOficinaDto> getOficinaByUsuarioGuiid(String guiidUsuario) {
        ResponseDto<GetOficinaDto> responseDto = new ResponseDto<>();

        Optional<GetOficinaDto> result = _oficinaMyRepository.getOficinaByUsuarioGuiid(guiidUsuario);
        if (result.isPresent()) {
            responseDto.setData(result.get());
        }

        return responseDto;
    }

    @Override
    public ResponseDto<Boolean> usuarioRegistrar(OficinaUsuarioRegistrarReqDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        Optional<OficinaUsuario> oficUsuario = _oficinaUsuarioRepository.findFirstByGuiidAndEstado(input.getGuiidUsuario(),1);
        if (oficUsuario.isPresent()) {
            throw new ServiceException("El usuario ya tiene asignada una oficina.");
        }

        Date fechaHoy = DateUtilities.currentDate();

        OficinaUsuario oficinaUsuario = new OficinaUsuario();
        oficinaUsuario.setIdOficina(oficina.get().getIdOficina());
        oficinaUsuario.setGuiid(input.getGuiidUsuario());
        oficinaUsuario.setUsuario(input.getUsuario());
        oficinaUsuario.setNombres(input.getNombres());
        oficinaUsuario.setFechaRegistro(fechaHoy);
        oficinaUsuario.setEstado(1);
        _oficinaUsuarioRepository.save(oficinaUsuario);

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

    @Override
    public ResponseDto<List<OficinaItemDto>> combo() {
        List<OficinaItemDto> list = _oficinaMyRepository.getCombo();
        ResponseDto<List<OficinaItemDto>> responseDto = new ResponseDto<>();
        responseDto.setData(list);
        return responseDto;
    }

    @Override
    public ResponseDto<PageInfo<OficinaListItemDto>> list(PaginationDto input) {
        PageHelper.startPage(input.getPageNum(), input.getPageSize());

        List<OficinaListItemDto> list = _oficinaMyRepository.getList(input.getFilter());
        PageInfo<OficinaListItemDto> pageInfo = new PageInfo<>(list);

        ResponseDto<PageInfo<OficinaListItemDto>> responseDto = new ResponseDto<>();
        responseDto.setData(pageInfo);
        return responseDto;
    }

    @Override
    public ResponseDto<OficinaListItemDto> get(Long id) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstById(id);
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        OficinaListItemDto data = new OficinaListItemDto();
        BeanUtils.copyProperties(oficina.get(), data);

        ResponseDto<OficinaListItemDto> responseDto = new ResponseDto<>();
        responseDto.setData(data);
        return responseDto;
    }

    @Override
    public ResponseDto<Long> register(OficinaRegisterReqDto input) {
        Oficina oficina = new Oficina();
        BeanUtils.copyProperties(input, oficina);

        Date currentDate = DateUtilities.currentDate();
        oficina.setFechaRegistro(currentDate);
        oficina.setEstado(1);
        oficina = _oficinaRepository.save(oficina);

        ResponseDto<Long> responseDto = new ResponseDto<>();
        responseDto.setData(oficina.getIdOficina());
        return responseDto;
    }

    @Override
    public ResponseDto<Boolean> update(OficinaUpdateReqDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstById(input.getIdOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        if (!(input.getTipo() == TipoOficina.OSPE || input.getTipo() == TipoOficina.EMERGENCIA) || input.getTipo() == TipoOficina.EVENTO) {
            throw new ServiceException("El tipo de oficina no es valido.");
        }

        oficina.get().setCodigo(input.getCodigo());
        oficina.get().setNombre(input.getNombre());
        oficina.get().setCodOspe(input.getCodOspe());
        oficina.get().setVentanillas(input.getVentanillas());
        oficina.get().setMaxDiasAntiguedad(input.getMaxDiasAntiguedad());
        oficina.get().setTipo(input.getTipo());
        _oficinaRepository.save(oficina.get());

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

    @Override
    public ResponseDto<Boolean> delete(Long id) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstById(id);
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        oficina.get().setEstado(0);
        _oficinaRepository.save(oficina.get());

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

    @Override
    public ResponseDto<Boolean> updateVideo(MultipartFile videoFile, Long idOficina) {
        if (videoFile.isEmpty()) {
            throw new ServiceException("No se pudo obtener los datos del video.");
        }

        byte[] videoData;

        try {
            videoData = videoFile.getBytes();
        } catch (IOException e) {
            throw new ServiceException("No se pudo obtener los datos del video: " + e.getMessage());
        }

        String videoPath = tvVideoFolder + "{video}.mp4".replace("{video}", idOficina.toString());
        File file = new File(videoPath);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(videoData);
            fos.flush();
        } catch (IOException e) {
            throw new ServiceException("Ocurrio un error al guardar el video: " + e.getMessage());
        }

        ResponseDto<Boolean> responseDto = new ResponseDto<>();
        responseDto.setData(true);
        return responseDto;
    }

}
