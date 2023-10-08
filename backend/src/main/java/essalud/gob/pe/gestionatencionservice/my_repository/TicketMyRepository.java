package essalud.gob.pe.gestionatencionservice.my_repository;

import essalud.gob.pe.gestionatencionservice.dto.ticket.TicketActivoEnAtencionDto;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface TicketMyRepository {

    Optional<String> getSiguienteTicket(Map params);

    Optional<TicketActivoEnAtencionDto> getTicketActivoEnAtencion(Map params);

    Optional<TicketActivoEnAtencionDto> getTicketActivoSinSalida(Map params);

    Long getAsistentesCount(Long idOficina);

}
