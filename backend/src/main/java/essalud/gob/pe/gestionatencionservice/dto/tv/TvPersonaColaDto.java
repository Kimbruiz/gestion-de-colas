package essalud.gob.pe.gestionatencionservice.dto.tv;

import lombok.Data;

@Data
public class TvPersonaColaDto {
    private Long orden;
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String fecha;
    private String horaSalida;
    private String ventanilla;
    private String fechaRegistro;
    private String foto;
}
