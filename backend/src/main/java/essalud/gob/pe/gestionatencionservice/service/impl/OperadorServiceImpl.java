package essalud.gob.pe.gestionatencionservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import essalud.gob.pe.gestionatencionservice.client.UtilServiceClient;
import essalud.gob.pe.gestionatencionservice.common.constants.ResponseType;
import essalud.gob.pe.gestionatencionservice.components.EssiPacienteComponent;
import essalud.gob.pe.gestionatencionservice.dto.NewResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.ResponseDto;
import essalud.gob.pe.gestionatencionservice.dto.essi.EssiGetPacienteRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.essi.EssiPacienteDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaColaRequestDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaDatosDto;
import essalud.gob.pe.gestionatencionservice.dto.operador.OpPersonaTicket;
import essalud.gob.pe.gestionatencionservice.dto.util.CentroDto;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Oficina;
import essalud.gob.pe.gestionatencionservice.jpa.repository.OficinaRepository;
import essalud.gob.pe.gestionatencionservice.my_repository.OperadorMyRepository;
import essalud.gob.pe.gestionatencionservice.service.OperadorService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperadorServiceImpl implements OperadorService {

    private final OperadorMyRepository _operadorMyRepository;
    private final OficinaRepository _oficinaRepository;
    private final EssiPacienteComponent _essiPacienteComponent;

    @Override
    public ResponseDto<PageInfo<OpPersonaColaDto>> personaCola(OpPersonaColaRequestDto input) {
        Optional<Oficina> oficina = _oficinaRepository.findFirstByCodigo(input.getCodOficina());
        if (oficina.isEmpty()) {
            throw new ServiceException("No se encontro información de la oficina.");
        }

        PageHelper.startPage(input.getPageNum(), input.getPageSize());

        Map<String, Object> params = new HashMap<>();
        params.put("guiidOperador", input.getGuiidOperador());
        params.put("idOficina", oficina.get().getIdOficina());

        List<OpPersonaColaDto> list = _operadorMyRepository.getPersonaCola(params);
        PageInfo<OpPersonaColaDto> pageInfo = new PageInfo<>(list);

        ResponseDto<PageInfo<OpPersonaColaDto>> responseDto = new ResponseDto<>();
        responseDto.setData(pageInfo);
        return responseDto;
    }

    @Override
    public ResponseDto<OpPersonaDatosDto> personaDatos(String guiidTicket) {
        var data = _essiPacienteComponent.getPersonaDatosByTicket(guiidTicket);
        var response = new ResponseDto<OpPersonaDatosDto>();
        response.setData(data);
        return response;
    }

}
