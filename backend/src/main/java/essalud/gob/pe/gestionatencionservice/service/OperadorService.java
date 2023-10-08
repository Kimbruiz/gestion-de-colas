package essalud.gob.pe.gestionatencionservice.service;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaDatosDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.TvConsultarRequestDto;

import java.util.List;

public interface OperadorService {

    ResponseDto<PageInfo<OpPersonaColaDto>> personaCola(OpPersonaColaRequestDto input);

    ResponseDto<OpPersonaDatosDto> personaDatos(String guiidTicket);

}
