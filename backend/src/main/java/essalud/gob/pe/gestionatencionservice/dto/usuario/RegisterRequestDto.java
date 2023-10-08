package essalud.gob.pe.gestionatencionservice.dto.usuario;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String guiidAdmin;
    private String usuario;
    private String tipDocIden;
    private String numDocIden;
    private String password;
    private String email;
    private String nombres;
    private String rol;
    private Long idOficina;
}
