package com.hybris.api.poc.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;

public class CustomDeserializationContext extends DefaultDeserializationContext {
    private static final long serialVersionUID = 1L;


    public CustomDeserializationContext(DeserializerFactory df) {
        super(df, null);
    }

    public CustomDeserializationContext(CustomDeserializationContext src,
                                        DeserializationConfig config, JsonParser jp, InjectableValues values) {
        super(src, config, jp, values);
    }

    public CustomDeserializationContext(CustomDeserializationContext src, DeserializerFactory factory) {
        super(src, factory);
    }

    @Override
    public DefaultDeserializationContext createInstance(DeserializationConfig config,
                                                        JsonParser jp, InjectableValues values) {
        return new CustomDeserializationContext(this, config, jp, values);
    }

    @Override
    public DefaultDeserializationContext with(DeserializerFactory factory) {
        return new CustomDeserializationContext(this, factory);
    }


    @Override
    public JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object deserDef) throws JsonMappingException {
        final JsonDeserializer<Object> deser = super.deserializerInstance(annotated, deserDef);

        if (annotated.getAnnotation(IsOneOfTypeField.class) != null) {
            final IsOneOfTypeField isOneOfTypeField = annotated.getAnnotation(IsOneOfTypeField.class);

            return new GenericOneOfDeserializer(isOneOfTypeField.value());
        }
        return deser;
    }


//    class FixedDeserializerDeserializationConfig extends DeserializationConfig {
//
//        private final DeserializationConfig original;
//
//
//        protected FixedDeserializerDeserializationConfig(final DeserializationConfig original) {
//            super(original, original.getAttributes());
//            this.original = original;
//        }
//
//        @Override
//        public AnnotationIntrospector getAnnotationIntrospector() {
//            return original.getAnnotationIntrospector();
//        }
//    }


}