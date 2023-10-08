package essalud.gob.pe.gestionatencionservice.my_repository;

import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaTicket;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpTicketEnAtencionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface OperadorMyRepository {

    List<OpPersonaColaDto> getPersonaCola(Map params);

    Optional<OpTicketEnAtencionDto> getTicketEnAtencion(Map params);

    Optional<OpPersonaTicket> getPersonaTicket(String guiidTicket);

}
