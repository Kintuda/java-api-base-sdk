package com.api.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Serializer() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public <T> T stringToClass(Class<T> clazz, final String json) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public <T> String classToString(T clazz) throws JsonProcessingException {
        return objectMapper.writeValueAsString(clazz);
    }

    public ObjectMapper getMapper() {
        return objectMapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }
}
