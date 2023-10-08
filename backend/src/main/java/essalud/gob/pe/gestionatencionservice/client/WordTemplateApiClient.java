package essalud.gob.pe.gestionatencionservice.client;

import essalud.gob.pe.gestionatencionservice.common.interfaces.BasicAuthForWordTemplateApi;
import essalud.gob.pe.gestionatencionservice.dto.wt_api.WtApiProcessWordReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasicAuthForWordTemplateApi
@FeignClient(name = "wordtemplateapiclient", url = "${feign-clients.word-template-api.url}")
public interface WordTemplateApiClient {

    @PostMapping("Document/ProcessDoc")
    byte[] processWord(@RequestBody WtApiProcessWordReqDto input);

}
