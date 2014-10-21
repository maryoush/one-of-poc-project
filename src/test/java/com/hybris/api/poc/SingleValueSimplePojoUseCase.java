package com.hybris.api.poc;

import org.junit.Test;


import static com.hybris.api.poc.SimplePojo.DUMMYFIELD.*;
/**
 * Created by i303813 on 16/10/14.
 */
public class SingleValueSimplePojoUseCase {


    @Test
    public void useCaseGetValuesV4() {

        SimplePojo pojo = new SimplePojo();

        pojo.setDummyField(new BarType());

        pojo.setDummyField("foo");
        pojo.setDummyField(new FooType());

        final Object result = pojo.getDummyField(); //this is safe however could return null


        if (pojo.isDummyFieldOf(FOOTYPE)) {
            final FooType fooNotNull = pojo.getDummyField();//safe

            System.out.println(fooNotNull.getFoo()); //not null
        }

        //this can throw a CCException
        final FooType generic = pojo.getDummyField();//this is tricky


        if (pojo.isDummyFieldOf(STRING))//if of foo type or null
        {
            final FooType fooNotNull = pojo.getDummyField();//will cause ClassCast unless it is null

            System.out.println(fooNotNull.getFoo()); //then this will throw a NPE
        }

        if (!pojo.hasDummyFieldValue()) {
            System.out.println("it is null");
        }

    }


}
