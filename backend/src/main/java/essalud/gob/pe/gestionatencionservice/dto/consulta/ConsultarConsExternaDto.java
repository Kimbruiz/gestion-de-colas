package essalud.gob.pe.gestionatencionservice.dto.consulta;

import lombok.Data;

@Data
public class ConsultarConsExternaDto {
    private String areaHospitalaria;
    private String cas;
    private String diagnostico;
    private String fechaAtencion;
    private String profAsistencial;
    private String servHospitalario;
}
