package essalud.gob.pe.gestionatencionservice.common.model;

import lombok.Data;

import javax.annotation.PostConstruct;

@Data
public class ResponseWrapper {
    Integer status;
    String statusText;
    String message;
    String path;

    @PostConstruct
    public void initIt() {
        this.statusText = "OK";
        this.status = 200;
    }

    public ResponseWrapper() {
    }

    public ResponseWrapper(String statusText, String message) {
        this.statusText = statusText;
        this.message = message;
    }
}