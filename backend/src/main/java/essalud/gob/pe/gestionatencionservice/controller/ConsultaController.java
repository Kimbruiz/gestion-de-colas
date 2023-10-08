package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.consulta.ConsultarRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.consulta.ConsultarResponseDto;
import essalud.gob.pe.gestionatencionservice.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
public class ConsultaController extends BaseController {

    private final ConsultaService _consultaService;

    public ConsultaController(ConsultaService consultaService) {
        _consultaService = consultaService;
    }

    /*
    @PostMapping("consultar")
    public ResponseEntity<?> consultar(@RequestBody ConsultarRequestDto input) {
        ResponseDto<ConsultarResponseDto> result = _consultaService.consultar(input);
        return ResponseEntity.ok(result);
    }*/

}
