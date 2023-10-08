package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.Data;

@Data
public class EssiCitaEmitidaDto {
    private String citActMedNum;
    private String citApeNomPac;
    private String citAutoGenCod;
    private String citCenAsiCod;
    private String citCenAsiDes;
    private String citConCod;
    private String citConDes;
    private String citEstCita;
    private String citFecha;
    private String citHora;
    private String citNumDocIden;
    private String citOriCenAsiCod;
    private String citProfAsis;
    private String citServHosCod;
    private String citServHosDes;
    private String citTipAtencion;
    private String citTipDocIden;
    private String codEstCtrlSeg;
    private String desEstCtrlSeg;
    private String dirCoordLatitud;
    private String dirCoordLongitud;
    private String direccionAten;

    public boolean isCitaAnulada() {
        return this.citEstCita.equals("ANULADA");
    }

    public boolean isCitaPendiente() {
        return this.citEstCita.equals("PENDIENTE");
    }

    public boolean isCitaRemota() {
        String tipoAtencion = getCitTipAtencion().toUpperCase();
        return (tipoAtencion.contains("TELECONSULTA") || tipoAtencion.contains("TELEMONITOREO") || tipoAtencion.contains("TELEORIENTACION"));
    }

    public boolean isCitaPresencial() {
        return !isCitaRemota();
    }
}
