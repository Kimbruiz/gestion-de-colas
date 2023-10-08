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
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona", insertable = false, updatable = false, nullable = false)
    private Long idPersona;

    @Column(name="tipo_doc")
    private String tipoDoc;

    @Column(name="num_doc")
    private String numDoc;

    @Column(name="ape_paterno")
    private String apePaterno;

    @Column(name="ape_materno")
    private String apeMaterno;

    @Column(name="pri_nombre")
    private String priNombre;

    @Column(name="seg_nombre")
    private String segNombre;

    @Column(name="genero")
    private String genero;

    @Column(name="fec_nacimiento")
    private Date fecNacimiento;

    @Column(name="fecha_registro")
    private Date fechaRegistro;

    @Column(name="estado")
    private Integer estado;

    @Column(name = "foto")
    private String foto;

}
