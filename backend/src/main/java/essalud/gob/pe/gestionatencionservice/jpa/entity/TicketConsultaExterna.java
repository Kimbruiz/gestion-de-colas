package essalud.gob.pe.gestionatencionservice.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ticket_consulta_externa")
public class TicketConsultaExterna {

    @Id
    @Column(name="id_ticket")
    private Long idTicket;

    @Column(name="cod_serv_hosp")
    private String codServHosp;

    @Column(name="serv_hosp")
    private String servHosp;

    @Column(name="prof_asistencial")
    private String profAsistencial;

}
