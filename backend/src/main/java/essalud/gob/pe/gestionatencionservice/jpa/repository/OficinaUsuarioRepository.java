package essalud.gob.pe.gestionatencionservice.jpa.repository;

import essalud.gob.pe.gestionatencionservice.jpa.entity.OficinaUsuario;
import essalud.gob.pe.gestionatencionservice.jpa.entity.key.OficinaUsuarioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OficinaUsuarioRepository extends JpaRepository<OficinaUsuario, OficinaUsuarioKey> {

    Optional<OficinaUsuario> findFirstByGuiidAndEstado(String guiid, Integer estado);

}
