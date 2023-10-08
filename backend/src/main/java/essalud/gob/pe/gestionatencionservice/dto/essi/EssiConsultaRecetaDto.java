package essalud.gob.pe.gestionatencionservice.dto.essi;

import lombok.Data;

import java.util.List;

@Data
public class EssiConsultaRecetaDto {
    private String codEmergencia;
    private String codTopicoEmer;
    private String profAsiApeNom;
    private String codUsuCre;
    private String numActMedOri;
    private String profTipDocIde;
    private String codEspHos;
    private String codOriCenAsi;
    private String flagAtencionFar;
    private String codAutGen;
    private String direcCasAsis;
    private String desEstAtenRece;
    private String desEspHos;
    private String desAreaHos;
    private String desFarEss;
    private String desActHos;
    private String desCenQui;
    private String codCenAsis;
    private String desEmergencia;
    private String desTopicoEmer;
    private String codAreaHos;
    private String desSubActHos;
    private String numHisCli;
    private String profNumDocIde;
    private String codReceta;
    private String desCentroAsistencial;
    private String fechaEmision;
    private String fecVigencia;
    private String codActHos;
    private String codEstAtenRece;
    private String edadPacAten;
    private String fechaCre;
    private String codSubActHos;
    private List<EssiConsultaRecetaMedicamentoDto> recetaMedicametos;

    public boolean isRecogerAvailable() {
        return !desEstAtenRece.equals("TOTAL");
    }
}
