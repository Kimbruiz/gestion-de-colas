package essalud.gob.pe.gestionatencionservice.jpa.entity;

import essalud.gob.pe.gestionatencionservice.jpa.entity.key.OficinaUsuarioKey;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="oficina_usuario")
@IdClass(OficinaUsuarioKey.class)
public class OficinaUsuario {

    @Id
    @Column(name="id_oficina", nullable = false)
    private Long idOficina;

    @Id
    @Column(name="guiid", nullable = false)
    private String guiid;

    @Column(name="usuario")
    private String usuario;

    @Column(name="fecha_registro")
    private Date fechaRegistro;

    @Column(name="estado")
    private Integer estado;

    @Column(name="nombres")
    private String nombres;

}
