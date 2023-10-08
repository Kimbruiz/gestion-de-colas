package essalud.gob.pe.gestionatencionservice.dto.oficina;

import lombok.Data;

@Data
public class OficinaRegisterReqDto {
    private String codigo;
    private String nombre;
    private String codOspe;
    private int ventanillas;
    private int maxDiasAntiguedad;
    private int tipo; //1 = OSPE | 2 = EMERGENCIA
}
