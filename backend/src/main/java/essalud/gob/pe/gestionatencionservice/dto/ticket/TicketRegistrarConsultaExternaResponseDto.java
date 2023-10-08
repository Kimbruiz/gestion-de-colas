package essalud.gob.pe.gestionatencionservice.dto.ticket;

import lombok.Data;

import java.util.Date;

@Data
public class TicketRegistrarConsultaExternaResponseDto {
    private String codTicket;
    private Date fechaRegistro;
}
