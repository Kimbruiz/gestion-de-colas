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
@Table(name="oficina")
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_oficina", insertable = false, updatable = false, nullable = false)
    private Long idOficina;

    @Column(name="codigo")
    private String codigo;

    @Column(name="nombre")
    private String nombre;

    @Column(name="cod_ospe")
    private String codOspe;

    @Column(name="ventanillas")
    private Integer ventanillas;

    @Column(name="fecha_registro")
    private Date fechaRegistro;

    @Column(name="estado")
    private Integer estado;

    @Column(name="max_dias_antiguedad")
    private Integer maxDiasAntiguedad;

    @Column(name="tipo")
    private Integer tipo;

}
