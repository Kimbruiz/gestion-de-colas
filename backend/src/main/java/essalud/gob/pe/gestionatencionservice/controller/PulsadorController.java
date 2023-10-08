package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.TicketAtenderColaRequestDto;
import essalud.gob.pe.gestionatencionservice.service.PulsadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("puls")
@RequiredArgsConstructor
public class PulsadorController extends BaseController {

    private final PulsadorService _pulsadorService;

    //Atender Siguiente en la Cola
    @PostMapping("atender-cola")
    public ResponseEntity<?> atenderCola(@RequestBody TicketAtenderColaRequestDto input) {
        ResponseDto<String> result = _pulsadorService.atenderCola(input);
        return ResponseEntity.ok(result);
    }

}
