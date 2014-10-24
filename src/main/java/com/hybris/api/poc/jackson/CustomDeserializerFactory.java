package com.hybris.api.poc.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.hybris.api.poc.model.GenericOneOfDeserializer;
import com.hybris.api.poc.model.IsOneOfTypeField;

/**
 * Created by i303813 on 24/10/14.
 */
public class CustomDeserializerFactory extends BeanDeserializerFactory {


    public CustomDeserializerFactory(DeserializerFactoryConfig config) {
        super(config);
    }

    public static CustomDeserializerFactory createInstance() {
        return new CustomDeserializerFactory(new DeserializerFactoryConfig());
    }

    @Override
    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext ctxt, Annotated annotated) throws JsonMappingException {

        if (annotated.getAnnotation(IsOneOfTypeField.class) != null) {
            final IsOneOfTypeField isOneOfTypeField = annotated.getAnnotation(IsOneOfTypeField.class);

            return new GenericOneOfDeserializer(isOneOfTypeField.value());
        }

        return super.findDeserializerFromAnnotation(ctxt, annotated);
    }
}
