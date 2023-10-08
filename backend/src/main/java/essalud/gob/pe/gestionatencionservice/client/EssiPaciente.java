package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.common.interfaces.BasicAuthForEssiPaciente;
import essalud.gob.pe.gestionatencionservice.dto.essi.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@BasicAuthForEssiPaciente
@FeignClient(name = "essipacienteservice", url = "${feign-clients.essi-paciente.url}")
public interface EssiPaciente {

    //TODO: no se usa de momento
    @PostMapping("pCitasCextRWs")
    EssiResponseDto<List<EssiCitaEmitidaDto>> consultaCitasEmitidas(@RequestBody EssiCitaEmitidaRequestDto input);

    @PostMapping("pLoginMovilRWs")
    EssiResponseDto<List<EssiPacienteDto>> getPacienteEssi(@RequestBody EssiGetPacienteRequestDto input);

}
