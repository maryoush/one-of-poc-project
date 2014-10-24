package com.hybris.api.poc.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GenericOneOfDeserializer extends JsonDeserializer<Object> {

    private final Class<?>[] supportedClasses;

    public GenericOneOfDeserializer() {
        this(new Class[]{});
    }

    public GenericOneOfDeserializer(Class<?>... supportedClasses) {
        this.supportedClasses = supportedClasses;
    }

    protected Class<?>[] getSupportedClasses() {
        return supportedClasses;
    }

    @Override
    public Object deserialize(final JsonParser jp, DeserializationContext ctxt) throws IOException {

        return OneOfDeserializerUtility.deserialize(jp, getSupportedClasses());
    }


}