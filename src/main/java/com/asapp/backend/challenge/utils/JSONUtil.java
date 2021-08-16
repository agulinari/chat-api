package com.asapp.backend.challenge.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;

@Slf4j
public class JSONUtil {
    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOEXception while mapping object (" + data + ") to JSON");
        }
    }

    public static <T>  T jsonToData(String json, Class<T> clazz) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("IOEXception while mapping json (" + json + ") to Data", e);
            throw e;
        }
    }

    public static <T>  T jsonToData(String json, TypeReference typeReference) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("IOEXception while mapping json (" + json + ") to Data", e);
            throw e;
        }
    }
}
