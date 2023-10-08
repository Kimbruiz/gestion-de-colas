package essalud.gob.pe.gestionatencionservice.jpa.repository;

import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {

    //@Query("select c from Oficina c where c.estado = 1 and c.codigo = :codViva")
    //Optional<Oficina> findFirstByCodViva(@Param("codViva") String codViva);

    @Query("select c from Oficina c where c.estado = 1 and CAST(c.idOficina AS string) = :codigo")
    Optional<Oficina> findFirstByCodigo(@Param("codigo") String codigo);

    @Query("select c from Oficina c where c.estado = 1 and c.idOficina = :idOficina")
    Optional<Oficina> findFirstById(@Param("idOficina") Long idOficina);

}
