package essalud.gob.pe.gestionatencionservice.dto.operador;

import lombok.Data;

@Data
public class OpPersonaTicket {
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String apellidos;
    private String genero;
    private String fecNacimiento;
    private String edad;
    private String fecha;
    private String hora;
    private String oficina;
    private Integer numAten;
    private Integer tipoRegistro;
}
