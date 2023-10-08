package essalud.gob.pe.gestionatencionservice.jpa.repository;

import essalud.gob.pe.gestionatencionservice.jpa.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findFirstByTipoDocAndNumDoc(String tipoDoc, String numDoc);

}
