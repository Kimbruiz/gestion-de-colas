package essalud.gob.pe.gestionatencionservice.dto.oficina;

import lombok.Data;

@Data
public class GetOficinaDto {
    private String codigo;
    private String codViva;
    private String codOspe;
    private String nombre;
    private Integer ventanillas;
    private Integer tipo;
}
