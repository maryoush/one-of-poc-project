package com.hybris.api.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybris.api.poc.jackson.CustomObjectMapperFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Deserialization use cases test
 */
public class NestedPojoDeserializationCase {

    private static final String SCHEMA_NESTED_CASE = "{\n" +
            "  \"simple\" : \"simple field\",\n" +
            "  \"other\" : {\n" +
            "    \"foo\" : {\n" +
            "      \"foo\" : \"here is foo\"\n" +
            "    },\n" +
            "    \"bar\" : {\n" +
            "      \"bar\" : \"bar one \",\n" +
            "      \"baz\" : 12.1\n" +
            "    }\n" +
            "  }\n" +
            "}";


    private ObjectMapper getObjectMapper() {

        return CustomObjectMapperFactory.createMapper();
    }


    @Test
    public void assureNestedTypes() throws IOException {


        final NestedPojo deserializedPojo = getObjectMapper().readValue(SCHEMA_NESTED_CASE, NestedPojo.class);

        Assert.assertNotNull(deserializedPojo.getSimple());
        Assert.assertNotNull(deserializedPojo.getOther());

        Assert.assertTrue(deserializedPojo.isOtherOf(Map.class));



        Map map = deserializedPojo.getOther();

        Assert.assertNotNull(map.get("foo"));
        Assert.assertNotNull(map.get("bar"));


        //Assert.assertNotNull(deserializedPojo);
    }



}
