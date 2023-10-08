package essalud.gob.pe.gestionatencionservice.my_repository;

import essalud.gob.pe.gestionatencionservice.dto.tv.TvConsultaExternaDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.TvFarmaciaDto;
import essalud.gob.pe.gestionatencionservice.dto.tv.TvPersonaColaDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TvMyRepository {

    List<TvPersonaColaDto> getPersonaCola(Map params);

    List<TvConsultaExternaDto> getConsultaExterna(Map params);

    List<TvFarmaciaDto> getFarmacia(Map params);

}
