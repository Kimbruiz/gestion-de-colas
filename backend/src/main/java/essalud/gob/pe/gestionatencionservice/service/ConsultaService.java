package essalud.gob.pe.gestionatencionservice.service;

import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.consulta.ConsultarRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.consulta.ConsultarResponseDto;

public interface ConsultaService {

    ResponseDto<ConsultarResponseDto> consultar(ConsultarRequestDto input);

}
