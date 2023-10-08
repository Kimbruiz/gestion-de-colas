package essalud.gob.pe.gestionatencionservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.*;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.TvMyRepository;
import essalud.gob.pe.gestionatencionservice.service.TvService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {

    private final TvMyRepository _tvMyRepository;
    private final OficinaRepository _oficinaRepository;

    @Value("${tv-video-folder}")
    private String tvVideoFolder;

    @Override
    public ResponseDto<PageInfo<TvPersonaColaDto>> personaCola(TvPersonaColaRequestDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("idOficina", oficina.get().getIdOficina());
        params.put("tipoOficina", oficina.get().getTipo());

        PageHelper.startPage(input.getPageNum(), input.getPageSize());

        List<TvPersonaColaDto> list = _tvMyRepository.getPersonaCola(params);
        PageInfo<TvPersonaColaDto> pageInfo = new PageInfo<>(list);

        ResponseDto<PageInfo<TvPersonaColaDto>> responseDto = new ResponseDto<>();
        responseDto.setData(pageInfo);
        return responseDto;
    }

    @Override
    public ResponseDto<List<TvConsultaExternaDto>> consultaExterna(TvConsultarRequestDto input) {
        Map<String, String> params = new HashMap<>();
        params.put("codOriCentro", input.getCodOriCentro());
        params.put("codRed", input.getCodRed());
        params.put("codCentro", input.getCodCentro());

        /*if (!StringUtilities.isNullOrEmpty(input.getServHosp())) {
            params.put("servHosp", input.getServHosp());
        }*/

        List<TvConsultaExternaDto> data = _tvMyRepository.getConsultaExterna(params);

        ResponseDto<List<TvConsultaExternaDto>> responseDto = new ResponseDto<>();
        responseDto.setData(data);
        return responseDto;
    }

    @Override
    public ResponseDto<List<TvFarmaciaDto>> farmacia(TvConsultarRequestDto input) {
        Map<String, String> params = new HashMap<>();
        params.put("codOriCentro", input.getCodOriCentro());
        params.put("codRed", input.getCodRed());
        params.put("codCentro", input.getCodCentro());

        List<TvFarmaciaDto> data = _tvMyRepository.getFarmacia(params);

        ResponseDto<List<TvFarmaciaDto>> responseDto = new ResponseDto<>();
        responseDto.setData(data);
        return responseDto;
    }

    @Override
    public File video(String codOficina) {
        String videoPath = tvVideoFolder + "{video}.mp4".replace("{video}", codOficina);
        File file = new File(videoPath);

        if (!file.exists())
            throw new ServiceException("No se pudo encontrar el video. ({0})"
                    .replace("{0}", videoPath));

        return file;
    }

}
