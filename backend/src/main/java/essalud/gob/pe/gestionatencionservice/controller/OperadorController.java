package essalud.gob.pe.gestionatencionservice.controller;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaDatosDto;
import essalud.gob.pe.gestionatencionservice.service.OperadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("op")
@RequiredArgsConstructor
public class OperadorController extends BaseController {

    private final OperadorService _operadorService;

    @PostMapping("persona-cola")
    public ResponseEntity<?> personaCola(@RequestBody OpPersonaColaRequestDto input) {
        ResponseDto<PageInfo<OpPersonaColaDto>> result = _operadorService.personaCola(input);
        return ResponseEntity.ok(result);
    }

    @GetMapping("persona-datos/{guiid}")
    public ResponseEntity<?> personaDatos(@PathVariable("guiid") String guiid) {
        ResponseDto<OpPersonaDatosDto> result = _operadorService.personaDatos(guiid);
        return ResponseEntity.ok(result);
    }

}
