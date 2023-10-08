package essalud.gob.pe.gestionatencionservice.service;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.*;

import java.io.File;
import java.util.List;

public interface TvService {

    ResponseDto<PageInfo<TvPersonaColaDto>> personaCola(TvPersonaColaRequestDto input);

    ResponseDto<List<TvConsultaExternaDto>> consultaExterna(TvConsultarRequestDto input);

    ResponseDto<List<TvFarmaciaDto>> farmacia(TvConsultarRequestDto input);

    File video(String codOficina);

}
