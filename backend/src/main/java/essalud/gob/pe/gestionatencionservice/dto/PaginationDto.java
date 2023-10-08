package essalud.gob.pe.gestionatencionservice.dto;

import lombok.Data;

@Data
public class PaginationDto {
    private int pageNum;
    private int pageSize;
    private String filter;
    private Long id;
}