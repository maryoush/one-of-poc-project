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
 * Dummy api use case test
 */
public class SimplePojoUseCase {


    @Test
    public void useCaseGetValues() {

        SimplePojo pojo = new SimplePojo();

        pojo.setDummy(new BarType());

        pojo.setDummy("foo");
        pojo.setDummy(new FooType());

        final Object result = pojo.getDummy(); //this is safe however could return null


        if (pojo.isDummyOf(FooType.class)) {
            final FooType fooNotNull = pojo.getDummy();//safe

            System.out.println(fooNotNull.getFoo()); //not null
        }

        //this can throw a CCException
        final FooType generic = pojo.getDummy();//this is tricky


        if (pojo.isDummyOf(String.class))//if of foo type or null
        {
            final FooType fooNotNull = pojo.getDummy();//will cause ClassCast unless it is null

            System.out.println(fooNotNull.getFoo()); //then this will throw a NPE
        }

        if (!pojo.hasDummyValue()) {
            System.out.println("it is null");
        }

    }



}
