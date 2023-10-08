package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

import java.util.Date;

@Data
public class TicketRegistrarGenericResponseDto {
    private String codTicket;
    private Date fechaRegistro;
    private String guiidTicket;

    private Integer tipoRegistro;
    private Integer tipoRespuesta;
    private boolean sobreescribir;
}
