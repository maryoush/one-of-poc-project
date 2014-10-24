package com.hybris.api.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.hybris.api.poc.jackson.CustomObjectMapperFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Serialization use cases test
 */
public class NestedPojoSerializationCase {


    private ObjectMapper getObjectMapper() {
        return CustomObjectMapperFactory.createMapper();
    }


    @Test
    public void assureNestedCase() throws IOException {

        BarType bar = new BarType();
        bar.setBar("bar one ");
        bar.setBaz(12.1);


        FooType foo = new FooType();
        foo.setFoo("here is foo");

        Map map = ImmutableMap.of("foo", foo,
                "bar", bar);


        NestedPojo root = new NestedPojo();
        root.setSimple("simple field");
        root.setOther(map);

        final String serializedPojo = getObjectMapper().writeValueAsString(root);

        final String expected = "{\n" +
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

        Assert.assertEquals(expected, serializedPojo);


    }

}
