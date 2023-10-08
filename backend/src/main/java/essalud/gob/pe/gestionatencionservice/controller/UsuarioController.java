package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.usuario.RegisterRequestDto;
import essalud.gob.pe.gestionatencionservice.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UsuarioController extends BaseController {

    private final UsuarioService usuarioService;

    @PostMapping("list")
    public ResponseEntity<?> list(@RequestBody PaginationDto input) {
        return ResponseEntity.ok(usuarioService.getUsuariosOficina(input));
    }

    @GetMapping("get/{guiid}")
    public ResponseEntity<?> get(@PathVariable("guiid") String guiid) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto input) {
        return ResponseEntity.ok(usuarioService.register(input));
    }

    @PostMapping("update")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok(null);
    }

}
