package essalud.gob.pe.gestionatencionservice.dto.auth;

import lombok.Data;

@Data
public class UserInfoDto {
    private String codOriCentro;
    private String codRed;
    private String codCentro;
    private Integer tipo; // 0 = COLA NORMAL / 1 = CONSULTAS
}
