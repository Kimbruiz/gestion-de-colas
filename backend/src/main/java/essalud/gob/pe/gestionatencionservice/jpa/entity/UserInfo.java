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
@Table(name="user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user_info", insertable = false, updatable = false, nullable = false)
    private Long idUserInfo;

    @Column(name="guiid")
    private String guiid;

    @Column(name="usuario")
    private String usuario;

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

}
