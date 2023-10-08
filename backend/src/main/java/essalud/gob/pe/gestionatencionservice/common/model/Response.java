package essalud.gob.pe.gestionatencionservice.common.model;

public class Response<T> extends ResponseWrapper {

    public Response() {
        super.initIt();
    }

    public Response(Integer status, String statusText, String message) {
        this.setStatus(status);
        this.setStatusText(statusText);
        this.setMessage(message);
    }
}