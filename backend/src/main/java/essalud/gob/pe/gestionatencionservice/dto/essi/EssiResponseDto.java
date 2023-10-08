package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.Data;

@Data
public class EssiResponseDto<T> {
    private String codError;
    private String desError;
    private T vDataItem;

    // Mantener para la serialización, con lombok se serializa a null
    public T getvDataItem() {
        return vDataItem;
    }

    public void setvDataItem(T vDataItem) {
        this.vDataItem = vDataItem;
    }
}
