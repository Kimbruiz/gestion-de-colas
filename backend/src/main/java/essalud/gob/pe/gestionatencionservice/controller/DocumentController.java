package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.common.base.BaseController;
import essalud.gob.pe.gestionatencionservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doc")
@RequiredArgsConstructor
public class DocumentController extends BaseController {

    private final DocumentService _documentService;

    @GetMapping("ticket-triaje/{guiidTicket}")
    public ResponseEntity<?> getTicketTriaje(@PathVariable("guiidTicket") String guiidTicket) {
        byte[] result = _documentService.getDocTicketTriaje(guiidTicket);
        return ResponseEntity.ok(result);
    }

}
