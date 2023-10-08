package essalud.gob.pe.gestionatencionservice.service.impl;

import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.dto.version.VersionDto;
import essalud.gob.pe.gestionatencionservice.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl extends BaseService implements VersionService {

    @Value("${version.number}")
    private String versionNumber;

    @Value("${version.url}")
    private String versionUrl;

    @Value("${version.label}")
    private String versionLabel;

    @Override
    public VersionDto getVersion() {
        return VersionDto.builder()
            .versionNumber(versionNumber)
            .versionUrl(versionUrl)
            .versionLabel(versionLabel)
            .build();
    }

}
