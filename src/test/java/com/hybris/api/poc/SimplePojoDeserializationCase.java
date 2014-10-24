package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.hybris.api.poc.mapper.CustomDeserializationContext;
import com.hybris.api.poc.mapper.CustomObjectMapperFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Deserialization use cases test
 */
public class SimplePojoDeserializationCase {


    private static final String SCHEMA_BAR_TYPE = "{\n" +

            "  \"simple\" : \"simple\",\n" +
            "  \"dummy\" : {\n" +
            "    \"bar\" : \"bar here for dummy\",\n" +
            "    \"baz\" : 10\n" +
            "  },\n" +
            "  \"other\" : {\n" +
            "    \"bar\" : \"bar other\",\n" +
            "    \"baz\" : 10\n" +
            "  }\n" +
            "}";


    private static final String SCHEMA_FOO_TYPE = "{\n" +

            "  \"simple\" : \"simple\",\n" +
            "  \"dummy\" : {\n" +
            "    \"foo\" : \"hi foo as dummy\"\n" +
            "  }\n" +
            "}";

    private static final String SCHEMA_AMBIGUOUS = "{\n" +
            "  \"simple\" : \"simple\",\n" +
            "  \"dummy\" : {\n" +
            "    \"bar\" : \"bar\",\n" +
            "    \"baz\" : 10,\n" +
            "    \"foo\" : \"some foo\"\n" +
            "  },\n" +
            "  \"other\" : {\n" +
            "    \"foo\" : \"hi there\"\n" +
            "  }\n" +
            "}";

    private static final String SCHEMA_STRING_TYPE = "{\n" +

            "  \"simple\" : \"simple\",\n" +
            "  \"dummy\" :   \"string here for dummy\"\n" +
            "  \n" +
            "}";


    private static final String SCHEMA_NUMBER_TYPE = "{\n" +
            "  \"other\" : 123\n" +
            "}";

    private ObjectMapper getObjectMapper() {

        return CustomObjectMapperFactory.createMapper();
    }


    @Test(expected = JsonMappingException.class)
    public void useCaseDeserializationForWrongSchema() throws IOException {

        ObjectMapper mapper = getObjectMapper();

        Assert.assertTrue(mapper.canSerialize(SimplePojo.class));

        SimplePojo pojo = mapper.readValue(SCHEMA_AMBIGUOUS, SimplePojo.class);

        Assert.assertNotNull(pojo);

    }

    @Test
    public void assureOneOfBindsToBar() throws IOException {


        final SimplePojo deserializedPojo = getObjectMapper().readValue(SCHEMA_BAR_TYPE, SimplePojo.class);

        Assert.assertNotNull(deserializedPojo.getDummy());
        Assert.assertNotNull(deserializedPojo.getOther());

        Assert.assertTrue(deserializedPojo.isDummyOf(BarType.class));
        Assert.assertTrue(deserializedPojo.isOtherOf(BarType.class));


        Assert.assertNotNull(deserializedPojo);
    }

    /**
     * other property is empty since it does not support foo type
     *
     * @throws IOException
     */
    @Test
    public void assureOneOfBindsToFooType() throws IOException {


        final SimplePojo deserializedPojo = getObjectMapper().readValue(SCHEMA_FOO_TYPE, SimplePojo.class);

        Assert.assertNotNull(deserializedPojo.getDummy());
        Assert.assertNull(deserializedPojo.getOther());

        Assert.assertTrue(deserializedPojo.isDummyOf(FooType.class));
        Assert.assertFalse(deserializedPojo.isOtherOf(FooType.class));


        Assert.assertNotNull(deserializedPojo);
    }


    /**
     * other is null  since it does not support string
     *
     * @throws IOException
     */
    @Test
    public void assureOneOfBindsToString() throws IOException {


        final SimplePojo deserializedPojo = getObjectMapper().readValue(SCHEMA_STRING_TYPE, SimplePojo.class);

        Assert.assertNotNull(deserializedPojo.getDummy());
        Assert.assertNull(deserializedPojo.getOther());

        Assert.assertTrue(deserializedPojo.isDummyOf(String.class));
        Assert.assertFalse(deserializedPojo.isOtherOf(String.class));


        Assert.assertNotNull(deserializedPojo);
    }

    /**
     * only other is not  null  since it does support number
     *
     * @throws IOException
     */
    @Test
    public void assureOneOfBindsToNumber() throws IOException {


        final SimplePojo deserializedPojo = getObjectMapper().readValue(SCHEMA_NUMBER_TYPE, SimplePojo.class);

        Assert.assertNull(deserializedPojo.getDummy());
        Assert.assertNotNull(deserializedPojo.getOther());

        Assert.assertFalse(deserializedPojo.isDummyOf(Number.class));
        Assert.assertTrue(deserializedPojo.isOtherOf(Number.class));


        Assert.assertNotNull(deserializedPojo);
    }


}
