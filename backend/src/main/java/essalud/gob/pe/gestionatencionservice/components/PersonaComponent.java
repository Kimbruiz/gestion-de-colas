package essalud.gob.pe.gestionatencionservice.components;

import essalud.gob.pe.gestionatencionservice.client.IdeapiClient;
import essalud.gob.pe.gestionatencionservice.common.base.BaseService;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoBuscarPersona;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoDocumento;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoOficina;
import essalud.gob.pe.gestionatencionservice.common.constants.TipoRegistroCola;
import essalud.gob.pe.gestionatencionservice.dto.ideapi.*;
import essalud.gob.pe.gestionatencionservice.dto.persona.TicketPersonaDto;
import essalud.gob.pe.gestionatencionservice.jpa.entity.Persona;
import essalud.gob.pe.gestionatencionservice.jpa.repository.PersonaRepository;
import essalud.gob.pe.gestionatencionservice.util.DateUtilities;
import essalud.gob.pe.gestionatencionservice.util.JsonUtilities;
import essalud.gob.pe.gestionatencionservice.util.StringUtilities;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonaComponent extends BaseService {

    private final IdeapiClient _ideapiClient;
    private final PersonaRepository _personaRepository;

    private IdeapiDetailDto _getData(IdeapiDetail detail) {

        return IdeapiDetailDto.builder()
                .fallecido(detail.isFallecido())
                .mayorEdad(detail.isMayorEdad())
                .priNombre(detail.getPriNombre())
                .segNombre(detail.getSegNombre())
                .apePaterno(detail.getApePaterno())
                .apeMaterno(detail.getApeMaterno())
                .sexo(detail.getSexo())
                .fecNac(detail.getFecNac())
                .fotoBase64(detail.getFoto())
                .build();
    }

    private Optional<IdeapiDetailDto> _buscar(String tipoDoc, String numDoc, Integer tipoOficina) {

        Optional<IdeapiDetailDto> response = Optional.empty();
        String sTipoDoc = StringUtilities.leftPadDiff(tipoDoc,"0",2);

        try {

            String file = "0"; //NO OBTIENE LA FOTO BASE64
            if (Objects.equals(tipoOficina, TipoOficina.EVENTO)) {
                file = "1"; //OBTIENE LA FOTO BASE64
            }

            IdeapiResponseDto<LinkedHashMap<String, Object>> result = _ideapiClient.busqueda(sTipoDoc,numDoc,file);


            if (result.getData() != null) {

                IdeapiDetailDto data = null;

                if (tipoDoc.equals(TipoDocumento.DNI)) {
                    var obj = JsonUtilities.convertMapToObject(result.getData(), IdeapiDniDto.class);
                    data = this._getData(obj);
                }
                else if (tipoDoc.equals(TipoDocumento.CE)) {
                    var obj = JsonUtilities.convertMapToObject(result.getData(), IdeapiCeDto.class);
                    data = this._getData(obj);
                }

                if (data != null) {
                    response = Optional.of(data);
                }

            }
        }
        catch (Exception ex) {
            loggerError("ideaapi-buscar ({0}-{1})"
                    .replace("{0}",sTipoDoc)
                    .replace("{1}",numDoc), ex.getMessage());
        }

        return response;
    }

    public boolean guardarPersona(Persona persona, Integer tipoAccionPersona) {

        if (Objects.equals(tipoAccionPersona, TipoBuscarPersona.REGISTRAR) ||
            Objects.equals(tipoAccionPersona, TipoBuscarPersona.ACTUALIZAR)) {

            _personaRepository.save(persona);
            return true;
        }

        return false;
    }

    //Solo busca, no registra / actualiza
    @SneakyThrows
    public TicketPersonaDto buscarPersona(String tipoDoc, String numDoc, Integer tipoRegistro, Integer tipoOficina) {

        TicketPersonaDto responseDto = new TicketPersonaDto();
        responseDto.setTipo(TipoBuscarPersona.SIN_INFORMACION);
        responseDto.setTipoRegistro(tipoRegistro);
        responseDto.setMessage("No se encontro información de la persona o sus datos no son validos.");

        Optional<Persona> persona;

        if (Objects.equals(tipoRegistro, TipoRegistroCola.NORMAL)) {

            //1. Buscar a la persona en la base de datos del sistema: GESTION DE ATENCION
            persona = _personaRepository.findFirstByTipoDocAndNumDoc(tipoDoc, numDoc);

            if (!Objects.equals(tipoOficina, TipoOficina.EVENTO)) {
                if (persona.isPresent()) {
                    if (!StringUtilities.isNullOrEmpty(persona.get().getPriNombre())) {
                        responseDto.setPersona(persona.get());
                        responseDto.setFound(true);
                        return responseDto;
                    }
                }
            }

            //2. Buscar a la persona en ESSI (IDEAPI)
            Optional<IdeapiDetailDto> personaBuscarResult = this._buscar(tipoDoc, numDoc, tipoOficina);
            if (personaBuscarResult.isPresent()) {

                //Si la persona esta como "Fallecido", no se le permite
                if (personaBuscarResult.get().isFallecido()) {
                    responseDto.setMessage("La persona se encuentra como fallecido.");
                    responseDto.setTipo(TipoBuscarPersona.ERROR);
                    return responseDto;
                }

                //Si la persona "No es Mayor de Edad", no se le permite
                if (!personaBuscarResult.get().isMayorEdad()) {
                    responseDto.setMessage("La persona no es mayor de edad.");
                    responseDto.setTipo(TipoBuscarPersona.ERROR);
                    return responseDto;
                }

                if (persona.isPresent()) {

                    //Actualizar
                    persona.get().setPriNombre(personaBuscarResult.get().getPriNombre());
                    persona.get().setSegNombre(personaBuscarResult.get().getSegNombre());
                    persona.get().setApePaterno(personaBuscarResult.get().getApePaterno());
                    persona.get().setApeMaterno(personaBuscarResult.get().getApeMaterno());
                    persona.get().setGenero(personaBuscarResult.get().getSexo());
                    persona.get().setFecNacimiento(personaBuscarResult.get().getFecNac());
                    persona.get().setFoto(personaBuscarResult.get().getFotoBase64());

                    responseDto.setPersona(persona.get());
                    responseDto.setFound(true);
                    responseDto.setTipo(TipoBuscarPersona.ACTUALIZAR);
                    return responseDto;
                }
                else {
                    //Registrar

                    Date fechaHoy = DateUtilities.currentDate();

                    persona = Optional.of(new Persona());
                    persona.get().setTipoDoc(tipoDoc);
                    persona.get().setNumDoc(numDoc);
                    persona.get().setPriNombre(personaBuscarResult.get().getPriNombre());
                    persona.get().setSegNombre(personaBuscarResult.get().getSegNombre());
                    persona.get().setApePaterno(personaBuscarResult.get().getApePaterno());
                    persona.get().setApeMaterno(personaBuscarResult.get().getApeMaterno());
                    persona.get().setGenero(personaBuscarResult.get().getSexo());
                    persona.get().setFecNacimiento(personaBuscarResult.get().getFecNac());
                    persona.get().setFechaRegistro(fechaHoy);
                    persona.get().setEstado(1);
                    persona.get().setFoto(personaBuscarResult.get().getFotoBase64());

                    responseDto.setPersona(persona.get());
                    responseDto.setFound(true);
                    responseDto.setTipo(TipoBuscarPersona.REGISTRAR);
                    return responseDto;
                }
            }
            else {

                if (persona.isPresent()) {
                    responseDto.setPersona(persona.get());
                    responseDto.setFound(true);
                    responseDto.setTipoRegistro(TipoRegistroCola.SIN_DATOS);
                    return responseDto;
                }

            }
        }
        else if (Objects.equals(tipoRegistro, TipoRegistroCola.SIN_DATOS)) {

            //1. Buscar a la persona en la base de datos del sistema: GESTION DE ATENCION
            persona = _personaRepository.findFirstByTipoDocAndNumDoc(tipoDoc, numDoc);
            if (persona.isPresent()) {
                responseDto.setPersona(persona.get());
                responseDto.setFound(true);
                return responseDto;
            }
            else {
                Date fechaHoy = DateUtilities.currentDate();

                persona = Optional.of(new Persona());
                persona.get().setTipoDoc(tipoDoc);
                persona.get().setNumDoc(numDoc);
                persona.get().setFechaRegistro(fechaHoy);
                persona.get().setEstado(1);

                responseDto.setPersona(persona.get());
                responseDto.setFound(true);
                responseDto.setTipo(TipoBuscarPersona.REGISTRAR);
                return responseDto;
            }
        }

        return responseDto;
    }

}
