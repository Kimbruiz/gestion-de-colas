package essalud.gob.pe.gestionatencionservice.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonUtilities {

    public static <T> Map<String, String> convertObjectToMap(T object) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (fieldValue != null) {
                map.put(field.getName(), fieldValue.toString());
            }
        }

        return map;
    }

}
