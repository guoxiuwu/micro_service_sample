package com.ebao.gs.sp.rc.common.utils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

public class JSONUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss.SSS"))
    }

    /**
     * return jackson ObjectMapper,for internal use only
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    /**
     * convert object to json
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        if (object == null) {
            return null;
        }
        try {
            String jsonString = mapper.writeValueAsString(object);
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * convert object from json
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        if (json == null || json.trim().equals("")) {
            return null;
        }
        try {
           mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss.SSS"))
            Object object = mapper.readValue(json, clazz);
            return (T) object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * convert object from json
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJSONAsList(String json, Class<T> clazz) {
        if (json == null || json.trim().equals("")) {
            return null;
        }
        try {
            JavaType type = mapper.getTypeFactory().
                    constructCollectionType(List.class, clazz);

            List<T> list = (List<T>) mapper.readValue(json, type);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> fromJSONAsMap(String json) {
        if (json == null || json.trim().equals("")) {
            return null;
        }
        try {
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map parseMap(String json) {
        if (json == null || json.trim().equals("")) {
            return null;
        }
        try {
            Map m = (Map) new JsonSlurper().parseText(json);
            Map r = new HashMap();
            r.putAll(m);
            return r;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
