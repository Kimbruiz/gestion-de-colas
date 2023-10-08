package essalud.gob.pe.gestionatencionservice.controller;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.PaginationDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.*;
import essalud.gob.pe.gestionatencionservice.service.OficinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("oficina")
@RequiredArgsConstructor
public class OficinaController extends BaseController {

    private final OficinaService _oficinaService;

    @GetMapping("usuario/{guiid}")
    public ResponseEntity<?> getOficinaByUsuarioGuiid(@PathVariable("guiid") String guiid) {
        ResponseDto<GetOficinaDto> data = _oficinaService.getOficinaByUsuarioGuiid(guiid);
        return ResponseEntity.ok(data);
    }

    @PostMapping("usuario/registrar")
    public ResponseEntity<?> usuarioRegistrar(@RequestBody OficinaUsuarioRegistrarReqDto input) {
        ResponseDto<Boolean> data = _oficinaService.usuarioRegistrar(input);
        return ResponseEntity.ok(data);
    }

    @GetMapping("combo")
    public ResponseEntity<?> combo() {
        ResponseDto<List<OficinaItemDto>> data = _oficinaService.combo();
        return ResponseEntity.ok(data);
    }

    @PostMapping("list")
    public ResponseEntity<?> list(@RequestBody PaginationDto input) {
        ResponseDto<PageInfo<OficinaListItemDto>> data = _oficinaService.list(input);
        return ResponseEntity.ok(data);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ResponseDto<OficinaListItemDto> data = _oficinaService.get(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody OficinaRegisterReqDto input) {
        return ResponseEntity.ok(_oficinaService.register(input));
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody OficinaUpdateReqDto input) {
        return ResponseEntity.ok(_oficinaService.update(input));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_oficinaService.delete(id));
    }

    @PostMapping("updateVideo/{id}")
    public ResponseEntity<?> updateVideo(
            @PathVariable("id") Long idOficina,
            @RequestPart("video") MultipartFile videoFile) {

        return ResponseEntity.ok(_oficinaService.updateVideo(videoFile, idOficina));
    }

}
