package essalud.gob.pe.gestionatencionservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

public abstract class JsonUtilities {

    public static <T> T convertMapToObject(LinkedHashMap<String, Object> map, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.convertValue(map, clazz);
    }

    public static <T> T jsonStringToObject(String jsonString, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.readValue(jsonString, valueType);
        } catch (JsonProcessingException e) {
            // Si la conversión falla, maneja la excepción y devuelve null
            System.err.println("Error al convertir el JSON string a " + valueType.getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

}
