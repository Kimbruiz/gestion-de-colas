package essalud.gob.pe.gestionatencionservice.dto.ideapi;

import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import lombok.*;

import java.util.Date;

@Data @ToString
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class IdeapiCeDto implements IdeapiDetail {
    private String numDocumento;
    private String feccaducidad;
    private String apematerno;
    private String domactual;
    private String painacionalidad;
    private String calmigratoria;
    private String fecActualizacion;
    private String nombres;
    private String fecnacimiento;
    private String tipoDocumento;
    private String apepaterno;
    private String fecvenresidencia;
    private String painacimiento;
    private String estcivil;
    private String genero;
    private String fecemision;
    private Object fecinscripcion;
    private String ubiactual;
    private Integer idePersonaMigraciones;
    private String ofimigratoria;

    @Override
    public boolean isFallecido() {
        return false;
    }

    @Override
    public boolean isMayorEdad() {
        int edad = DateUtilities.calculateAge(getFecNac());
        return (edad >= 18);
    }

    @Override
    public String getPriNombre() {
        int i = nombres.indexOf(" ");
        if (i != -1) {
            //tiene segundo nombre
            return nombres.substring(0,i);
        }

        //no tiene segundo nombre
        return nombres.trim();
    }

    @Override
    public String getSegNombre() {
        int i = nombres.indexOf(" ");
        if (i != -1) {
            //tiene segundo nombre
            return nombres.substring(i + 1).trim();
        }

        //no tiene segundo nombre
        return "";
    }

    @Override
    public String getApePaterno() {
        return apepaterno.trim();
    }

    @Override
    public String getApeMaterno() {
        if (apematerno == null)
            return null;

        return apematerno.trim();
    }

    @Override
    public String getSexo() {
        return genero.trim().startsWith("M") ? "M" : "F";
    }

    @Override
    @SneakyThrows
    public Date getFecNac() {
        return DateUtilities.stringDateToDate(fecnacimiento.trim());
    }

    @Override
    public String getFoto() {
        return null;
    }
}
