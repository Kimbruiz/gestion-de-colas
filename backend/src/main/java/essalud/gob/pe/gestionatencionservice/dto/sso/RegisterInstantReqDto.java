package essalud.gob.pe.gestionatencionservice.dto.sso;

import lombok.Data;

@Data
public class RegisterInstantReqDto {
    private String key;
    private String guiidAdmin;
    private String usuario;
    private String tipDocIden;
    private String numDocIden;
    private String password;
    private String email;
    private String nombres;
    private String codigoPlanilla;
    private String fechaInicioVigencia;
    private String fechaFinVigencia;
    private String observacion;
    private String rol;
}