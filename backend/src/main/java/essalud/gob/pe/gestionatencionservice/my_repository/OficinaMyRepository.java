package essalud.gob.pe.gestionatencionservice.my_repository;

import essalud.gob.pe.gestionatencionservice.dto.oficina.GetOficinaDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.OficinaItemDto;
import essalud.gob.pe.gestionatencionservice.dto.oficina.OficinaListItemDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OficinaMyRepository {

    Optional<GetOficinaDto> getOficinaByUsuarioGuiid(String guiidUsuario);

    Long getOficinasCountByUsuario(String usuario);

    List<OficinaItemDto> getCombo();

    List<OficinaListItemDto> getList(String filter);

}
