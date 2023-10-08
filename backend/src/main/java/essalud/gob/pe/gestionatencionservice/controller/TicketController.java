package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ticket.*;
import essalud.gob.pe.gestionatencionservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
@RequiredArgsConstructor
public class TicketController extends BaseController {

    private final TicketService _ticketService;

    @GetMapping("asistentes/{codOficina}")
    public ResponseEntity<?> getAsistentesCount(@PathVariable("codOficina") String codOficina) {
        return ResponseEntity.ok(_ticketService.getAsistentesCount(Long.valueOf(codOficina)));
    }

    @PostMapping("registrar-cola")
    public ResponseEntity<?> registrarCola(@RequestBody TicketRegistrarColaRequestDto input) {
        ResponseDto<TicketRegistrarGenericResponseDto> result = _ticketService.registrarCola(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("atender-cola")
    public ResponseEntity<?> atenderCola(@RequestBody TicketAtenderColaRequestDto input) {
        ResponseDto<String> result = _ticketService.atenderCola(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("atender-cola-esp")
    public ResponseEntity<?> atenderColaEspecifica(@RequestBody TicketAtenderColaEspRequestDto input) {
        ResponseDto<String> result = _ticketService.atenderColaEspecifica(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("finalizar-cola")
    public ResponseEntity<?> finalizarCola(@RequestBody TicketFinalizarColaRequestDto input) {
        ResponseDto<Boolean> result = _ticketService.finalizarCola(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("anular-cola")
    public ResponseEntity<?> anularCola(@RequestBody TicketAnularColaRequestDto input) {
        ResponseDto<Boolean> result = _ticketService.anularCola(input);
        return ResponseEntity.ok(result);
    }

    /*
    @PostMapping("registrar-consulta-externa")
    public ResponseEntity<?> registrarConsultaExterna(@RequestBody TicketRegistrarConsultaExternaRequestDto input) {
        ResponseDto<TicketRegistrarGenericResponseDto> result = _ticketService.registrarConsultaExterna(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("registrar-farmacia")
    public ResponseEntity<?> registrarFarmacia(@RequestBody TicketRegistrarFarmaciaRequestDto input) {
        ResponseDto<TicketRegistrarGenericResponseDto> result = _ticketService.registrarFarmacia(input);
        return ResponseEntity.ok(result);
    }*/

}
