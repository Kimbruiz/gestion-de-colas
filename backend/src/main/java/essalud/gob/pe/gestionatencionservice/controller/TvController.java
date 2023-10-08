package essalud.gob.pe.gestionatencionservice.controller;

import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.*;
import essalud.gob.pe.gestionatencionservice.service.TvService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("tv")
@RequiredArgsConstructor
public class TvController extends BaseController {

    private final TvService _tvService;

    @PostMapping("persona-cola")
    public ResponseEntity<?> personaCola(@RequestBody TvPersonaColaRequestDto input) {
        ResponseDto<PageInfo<TvPersonaColaDto>> result = _tvService.personaCola(input);
        return ResponseEntity.ok(result);
    }

    @GetMapping("video/{codOficina}")
    public ResponseEntity<?> video(@PathVariable("codOficina") String codOficina) {
        File file = _tvService.video(codOficina);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=video.mp4");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    /*@PostMapping("consulta-externa")
    public ResponseEntity<?> consultaExterna(@RequestBody TvConsultarRequestDto input) {
        ResponseDto<List<TvConsultaExternaDto>> result = _tvService.consultaExterna(input);
        return ResponseEntity.ok(result);
    }

    @PostMapping("farmacia")
    public ResponseEntity<?> farmacia(@RequestBody TvConsultarRequestDto input) {
        ResponseDto<List<TvFarmaciaDto>> result = _tvService.farmacia(input);
        return ResponseEntity.ok(result);
    }*/

}
