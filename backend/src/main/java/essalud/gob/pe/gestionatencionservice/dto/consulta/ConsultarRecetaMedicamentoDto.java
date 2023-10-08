package essalud.gob.pe.gestionatencionservice.dto.consulta;

import lombok.Data;

@Data
public class ConsultarRecetaMedicamentoDto {
    private String desMedicamento;
    private String cantidadSol;
    private String codUnidadAten;
    private String dosifiIndica;
    private Integer diasDuraCan;
}
