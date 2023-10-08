package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.common.interfaces.BasicAuthForUtilService;
import essalud.gob.pe.gestionatencionservice.dto.util.CentroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@BasicAuthForUtilService
@FeignClient(name = "utilserviceclient", url = "${feign-clients.util-service.url}")
public interface UtilServiceClient {

    @GetMapping("centro/{codCentro}")
    CentroDto getCentro(@PathVariable("codCentro") String codCentro);

}
