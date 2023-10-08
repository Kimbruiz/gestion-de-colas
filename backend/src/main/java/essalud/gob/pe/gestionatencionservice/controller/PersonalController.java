package essalud.gob.pe.gestionatencionservice.controller;

import essalud.gob.pe.gestionatencionservice.service.PersonalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personal")
public class PersonalController {

    private final PersonalService _personalService;

    public PersonalController(PersonalService personalService) {
        _personalService = personalService;
    }

    /*
    @PostMapping("actualizar-ticket")
    public ResponseEntity<?> actualizarTicket() {
        return ResponseEntity.ok("Actualizar Ticket !!!");
    }*/

}
