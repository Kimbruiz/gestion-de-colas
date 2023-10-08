package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.client.WordTemplateApiClient;
import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.components.EssiPacienteComponent;
import essalud.gob.pe.gestionatencionservice.dto.wt_api.WtApiProcessWordReqDto;
import essalud.gob.pe.gestionatencionservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl extends BaseService implements DocumentService {

    private final WordTemplateApiClient _wordTemplateApiClient;
    private final EssiPacienteComponent _essiPacienteComponent;

    @Value("${documents.ticket}")
    private String documentTicket;

    @Override
    public byte[] getDocTicketTriaje(String guiidTicket) {
        var personaDatos = _essiPacienteComponent.getPersonaDatosByTicket(guiidTicket);
        Map<String, String> data = personaDatos.getData();
        data.put("guiid", guiidTicket);

        var request = WtApiProcessWordReqDto.builder()
                .name(documentTicket)
                .data(data)
                .build();

        return _wordTemplateApiClient.processWord(request);
    }
}
