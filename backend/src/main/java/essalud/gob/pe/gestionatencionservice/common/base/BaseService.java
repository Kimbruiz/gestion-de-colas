package essalud.gob.pe.gestionatencionservice.common.base;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

@Data
public abstract class BaseService {

    private static final Logger logger = LogManager.getLogger(BaseService.class);

    @Autowired
    private HttpServletRequest request;

    private String logginInfo = "show";

    public HttpHeaders getHttpHeader(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.add("Content-Type", String.valueOf(mediaType));
        } catch (Exception e) {
            loggerException("Error al obtener headers...", e);
        }
        return headers;
    }

    public void loggerException(String title, Exception e) {
        logger.error(title, e);
    }

    public void loggerInfo(String title, String info) {
        if (logginInfo.contains("show"))
            logger.info(title + ":" + info);
    }

    public void loggerError(String title, String info) {
        if (logginInfo.contains("show"))
            logger.error(title + ":" + info);
    }

}
