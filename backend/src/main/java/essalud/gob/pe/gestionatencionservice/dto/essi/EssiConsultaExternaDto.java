package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.Data;

@Data
public class EssiConsultaExternaDto {
    private String areaHospitalaria;
    private String cas;
    private String diagnostico;
    private String fechaAtencion;
    private String profAsistencial;
    private String servHospitalario;
}
