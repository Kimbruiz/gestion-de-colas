package essalud.gob.pe.gestionatencionservice.dto.ideapi;

import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.*;

import java.util.Date;

@Data @ToString
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class IdeapiDniDto implements IdeapiDetail {
    private String txtNomConyuge;
    private String txtApepatconyuge;
    private String fecActualizacion;
    private String txtApepaterno;
    private String txtApematcasadaconyuge;
    private String codInterdiccion;
    private String flgReniec;
    private String codVerificacion;
    private String txtApematerno;
    private String txtApepatresponsable;
    private String txtNombres;
    private String numFoto;
    private String tipDocResponsable;
    private String txtGrdinstruccion;
    private String tipDocDeclarante;
    private String usuActualizacion;
    private String txtDireccion;
    private String txtApematresponsable;
    private String numDocMadre;
    private String codUbgDom;
    private String numDocSustentatorio;
    private String txtApematpadre;
    private String tipDocPadre;
    private String codActivo;
    private String txtEstatura;
    private String fecNacimiento;
    private String txtApematconyuge;
    private String txtApepatmadre;
    private String txtNomPadre;
    private String codVinculoDeclarante;
    private String tipDocSustentatorio;
    private String ideDni;
    private String tipDocMadre;
    private String numDocResponsable;
    private String txtNomMadre;
    private String fefallecid;
    private String fecIncripcion;
    private String indSunat;
    private String numDocConyuge;
    private String txtPrenombres;
    private String fecExpedicion;
    private String txtApematcasadaresponsable;
    private String aux5;
    private String txtReestricciones;
    private String txtNomDeclarante;
    private String tipDocConyuge;
    private String txtApematdeclarante;
    private String txtApematcasadadeclarante;
    private String codUbgNac;
    private String codDiscapacidad;
    private String codDeclarante;
    private String txtApematcasada;
    private String txtApepatdeclarante;
    private String anhoEstudio;
    private String txtApepatpadre;
    private String tipDoc;
    private String txtNomResponsable;
    private String txtApematmadre;
    private String numDocDeclarante;
    private String numDocPadre;
    private String codErrorReniec;
    private String codSexo;
    private String numFirma;
    private String txtApematcasadamadre;
    private String codEstcivil;
    private String fotoBase64;

    @Override
    public boolean isFallecido() {
        return (!StringUtilities.isNullOrEmpty(fefallecid));
    }

    @Override
    public boolean isMayorEdad() {
        int edad = DateUtilities.calculateAge(getFecNac());
        return (edad >= 18);
    }

    @Override
    public String getPriNombre() {
        int i = txtNombres.indexOf(" ");
        if (i != -1) {
            //tiene segundo nombre
            return txtNombres.substring(0,i);
        }

        //no tiene segundo nombre
        return txtNombres.trim();
    }

    @Override
    public String getSegNombre() {
        int i = txtNombres.indexOf(" ");
        if (i != -1) {
            //tiene segundo nombre
            return txtNombres.substring(i + 1).trim();
        }

        //no tiene segundo nombre
        return "";
    }

    @Override
    public String getApePaterno() {
        return txtApepaterno.trim();
    }

    @Override
    public String getApeMaterno() {
        if (txtApematerno == null)
            return null;

        return txtApematerno.trim();
    }

    @Override
    public String getSexo() {
        return codSexo.trim().equals("1") ? "M" : "F";
    }

    @Override
    @SneakyThrows
    public Date getFecNac() {
        return DateUtilities.stringDateToDate(fecNacimiento.trim());
    }

    @Override
    public String getFoto() {
        return fotoBase64;
    }
}