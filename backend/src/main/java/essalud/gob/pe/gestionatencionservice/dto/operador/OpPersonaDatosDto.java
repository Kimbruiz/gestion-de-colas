package essalud.gob.pe.gestionatencionservice.dto.operador;

import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class OpPersonaDatosDto {
    private String tipoDoc;
    private String numDoc;
    private String nombres;
    private String apellidos;
    private String fecNac;
    private String edad;
    private String red;
    private String ipress;
    private String vigenciaHasta;
    private String fecha;
    private String hora;
    private String oficina;
    private Integer numAten;

    public String getTipoDocDes() {
        return switch (tipoDoc) {
            case "1" -> "DNI";
            case "4" -> "CE";
            default -> "";
        };
    }

    public Map<String, String> getData() {
        Map<String, String> result = new HashMap<>();
        result.put("tipoDoc", getTipoDocDes());
        result.put("numDoc", getNumDoc());
        result.put("nombres", getNombres());
        result.put("apellidos", getApellidos());
        //result.put("fecNac", getFecNac());
        result.put("edad", getEdad());
        result.put("red", getRed());
        result.put("ipress", getIpress());
        result.put("vigenciaHasta", getVigenciaHasta());
        result.put("fecha", getFecha());
        result.put("hora", getHora());
        result.put("oficina", getOficina());
        result.put("numAten", StringUtilities.ifNull(numAten.toString(),"0"));
        return result;
    }
}
