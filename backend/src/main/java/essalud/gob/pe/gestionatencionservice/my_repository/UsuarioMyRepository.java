package essalud.gob.pe.gestionatencionservice.my_repository;

import essalud.gob.pe.gestionatencionservice.dto.usuario.UsuarioOficinaDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UsuarioMyRepository {

    List<UsuarioOficinaDto> getUsuariosOficina(Map params);

}
