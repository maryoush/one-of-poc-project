package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Serialization use cases test
 */
public class SimplePojoSerializationCase {


    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        return mapper;
    }



    @Test
    public void assureOneOfBindsToNonPrimitives() throws IOException {

        BarType bar1 = new BarType();
        bar1.setBar("bar one ");
        bar1.setBaz(12.1);

        BarType bar2 = new BarType();
        bar2.setBar("bar one ");
        bar2.setBaz(12.1);


        SimplePojo pojo = new SimplePojo();
        pojo.setDummy(bar1);
        pojo.setOther(bar2);
        pojo.setSimple("simple");


        final String serializedPojo = getObjectMapper().writeValueAsString(pojo);

        final String  expected = "{\n" +
                "  \"simple\" : \"simple\",\n" +
                "  \"dummy\" : {\n" +
                "    \"bar\" : \"bar one \",\n" +
                "    \"baz\" : 12.1\n" +
                "  },\n" +
                "  \"other\" : {\n" +
                "    \"bar\" : \"bar one \",\n" +
                "    \"baz\" : 12.1\n" +
                "  }\n" +
                "}";

        Assert.assertEquals(expected,serializedPojo);


    }



    @Test
    public void assureOneOfBindsToPrimitives() throws IOException {


        BarType bar1 = new BarType();
        bar1.setBar("bar one ");
        bar1.setBaz(12.1);

        BarType bar2 = new BarType();
        bar2.setBar("bar one ");
        bar2.setBaz(12.1);


        SimplePojo pojo = new SimplePojo();
        pojo.setDummy("dummy bar inlined");
        pojo.setOther(Integer.valueOf(12));
        pojo.setSimple("simple");


        final String serializedPojo = getObjectMapper().writeValueAsString(pojo);

        final String expected = "{\n" +
                "  \"simple\" : \"simple\",\n" +
                "  \"dummy\" : \"dummy bar inlined\",\n" +
                "  \"other\" : 12\n" +
                "}";

        Assert.assertEquals(expected,serializedPojo);
    }



}
