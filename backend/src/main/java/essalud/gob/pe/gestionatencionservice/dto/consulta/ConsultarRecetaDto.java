package essalud.gob.pe.gestionatencionservice.dto.consulta;

import lombok.Data;

import java.util.List;

@Data
public class ConsultarRecetaDto {
    private String codReceta;
    private String desCentroAsistencial;
    private String desEspHos;
    private String profAsiApeNom;
    private List<ConsultarRecetaMedicamentoDto> medicamentos;
}
