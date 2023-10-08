package essalud.gob.pe.gestionatencionservice.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ticket", insertable = false, updatable = false, nullable = false)
    private Long idTicket;

    @Column(name="id_persona")
    private Long idPersona;

    @Column(name="guiid")
    private String guiid;

    @Column(name="cod_ticket")
    private String codTicket;

    @Column(name="cod_ori_centro")
    private String codOriCentro;

    @Column(name="cod_red")
    private String codRed;

    @Column(name="cod_centro")
    private String codCentro;

    @Column(name="tipo")
    private Integer tipo;

    @Column(name="fecha_registro")
    private Date fechaRegistro;

    @Column(name="estado")
    private Integer estado;

    @Column(name="guiid_operador")
    private String guiidOperador;

    @Column(name="ventanilla")
    private String ventanilla;

    @Column(name="fecha_atendido")
    private Date fechaAtendido;

    @Column(name="fecha_finalizado")
    private Date fechaFinalizado;

    @Column(name="observacion")
    private String observacion;

    @Column(name="id_oficina")
    private Long idOficina;

    @Column(name="num_aten")
    private Integer numAten;

    @Column(name="tipo_registro")
    private Integer tipoRegistro;

    @Column(name="fecha_salida")
    private Date fechaSalida;

}
