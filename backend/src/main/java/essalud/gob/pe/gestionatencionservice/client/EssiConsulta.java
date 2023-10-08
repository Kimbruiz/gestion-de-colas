package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.common.interfaces.BasicAuthForEssiConsulta;
import essalud.gob.pe.gestionatencionservice.dto.essi.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@BasicAuthForEssiConsulta
@FeignClient(name = "essiconsultaservice", url = "${feign-clients.essi-consulta.url}")
public interface EssiConsulta {

    //TODO: no se usa de momento
    @PostMapping("consAtenCextWs")
    EssiResponseDto<List<EssiConsultaExternaDto>> consultaExterna(@RequestBody EssiGenericRequestDto input);

    //TODO: no se usa de momento
    @PostMapping("pRecePacRWs")
    EssiResponseDto<List<EssiConsultaRecetaDto>> consultaRecetas(@RequestBody EssiConsultaRecetaRequestDto input);

}
