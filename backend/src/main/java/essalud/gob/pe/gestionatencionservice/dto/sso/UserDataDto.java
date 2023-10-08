package essalud.gob.pe.gestionatencionservice.dto.sso;

import lombok.Data;

import java.util.List;

@Data
public class UserDataDto {
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String email;
    private List<RolesUsuarioDto> roles;
}