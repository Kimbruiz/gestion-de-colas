package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("version")
@RequiredArgsConstructor
public class VersionController extends BaseController {

    private final VersionService _versionService;

    @GetMapping
    public ResponseEntity<?> getVersion() {
        var result = _versionService.getVersion();
        return ResponseEntity.ok(result);
    }

}
