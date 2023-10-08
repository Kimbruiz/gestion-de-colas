package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.client.config.IdeApiClientConfig;
import essalud.gob.pe.gestionatencionservice.common.interfaces.BasicAuthForIdeApi;
import essalud.gob.pe.gestionatencionservice.dto.ideapi.IdeapiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@BasicAuthForIdeApi
@FeignClient(name = "ideapiclientservice", url = "${feign-clients.ide-api.url}", configuration = IdeApiClientConfig.class)
public interface IdeapiClient {

    @GetMapping("persona/busqueda?tipodoc={tipdoc}&numerodocumento={numdoc}&file={file}")
    <T> IdeapiResponseDto<T> busqueda(
            @PathVariable("tipdoc") String tipdoc,
            @PathVariable("numdoc") String numdoc,
            @PathVariable("file") String file);

}
