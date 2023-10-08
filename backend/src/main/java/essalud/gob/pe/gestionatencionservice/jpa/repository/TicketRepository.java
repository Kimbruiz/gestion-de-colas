package essalud.gob.pe.gestionatencionservice.jpa.repository;

import essalud.gob.pe.gestionatencionservice.jpa.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findFirstByGuiid(String guiid);

    @Modifying
    @Query("update Ticket t set t.estado = :nuevoEstado where t.guiid = :guiidTicket")
    int updateEstadoByGuiid(@Param("nuevoEstado") Integer nuevoEstado, @Param("guiidTicket") String guiidTicket);

    @Modifying
    @Query(value = "update ticket set fecha_salida = timezone('America/Lima', now()) where guiid = :guiidTicket", nativeQuery = true)
    int updateFechaSalidaByGuiid(@Param("guiidTicket") String guiidTicket);

    @Query(value = "SELECT COALESCE(MAX(num_aten), 0) + 1 " +
            "FROM ticket " +
            "WHERE CAST(fecha_registro AS DATE) = TO_DATE(:fecha, 'DD/MM/YYYY') and id_oficina = :idOficina", nativeQuery = true)
    int getNextNumAten(@Param("fecha") String fecha, @Param("idOficina") Long idOficina);

}
