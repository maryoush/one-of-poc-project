package com.hybris.api.poc;

import org.junit.Test;

import java.util.Optional;

import static com.hybris.api.poc.SimplePojoV6.*;

/**
 * Created by i303813 on 16/10/14.
 */
public class SingleValueSimplePojoUseCase {


    /**
     * retrieved a single value eg. by setting appropriate headers
     */
    @Test
    public void useCaseGetValuesV1() {
        SimplePojoV1 pojo = new SimplePojoV1();


        final Object result = pojo.getOneOfField().asObject();

        final Optional<FooType> resultFoo1 = pojo.getOneOfField().as(FooType.class);


        final Optional<String> resultFoo2 = pojo.getOneOfField().as(String.class);

        final BarType resultFoo3 = pojo.getOneOfField().asBarType();


    }


    @Test
    public void useCaseGetValuesV2() {
        SimplePojoV2 pojo = new SimplePojoV2("empty string");


        if (pojo.isFooType()) //is of foo type and not null
        {
            final FooType foo = pojo.getValueAsFoo();
            System.out.println(foo.getFoo()); //not null

        }

        pojo.getValueAsBar();//might return null

    }


    @Test
    public void useCaseGetValuesV3() {
        SimplePojoV3 pojo = new SimplePojoV3();


        final Object result = pojo.getOneOfFieldAsObject();


        //this can throw a CCException
        final Optional<FooType> foo = pojo.getOneOfFieldAs(FooType.class);


        if (foo.isPresent())//if of foo type or null
        {
            System.out.println(foo.get()); //not null
        }

    }


    @Test
    public void useCaseGetValuesV4() {
        SimplePojoV4 pojo = new SimplePojoV4(new BarType());


        final Object result = pojo.getValue(); //this is safe however could return null


        //this can throw a CCException
        final FooType foo = pojo.getValue();//this is tricky


        if (pojo.isOf(FooType.class))//if of foo type or null
        {
            final FooType fooNotNull = pojo.getValue();

            System.out.println(fooNotNull.getFoo()); //not null
        }

    }


    @Test
    public void useCaseGetValuesV5() {
        SimplePojoV5 pojo = new SimplePojoV5();
        pojo.setOneOfField(new BarType());

        final Object result = pojo.getOneOfField(); //this is safe however could return null


        if (IsOneOfUtil.isOf(result, FooType.class)) {
            final FooType fooNotNull = IsOneOfUtil.asOf(result);

            System.out.println(fooNotNull.getFoo()); //not null
        }

        GenericCasteable casteableOneOf = IsOneOfUtil.asCasteable(result);

        if (casteableOneOf.isNull()) {
            System.out.println("null");
        }

        if (casteableOneOf.isCompatibleWith(BarType.class)) {
            BarType bb = casteableOneOf.asOf(BarType.class);
        }

        try {
            casteableOneOf.asOf(FooType.class);
        } catch (IllegalArgumentException e) {
            //
        }


    }


    @Test
    public void useCaseGetValuesV6() {
        SimplePojoV6 pojo = new SimplePojoV6();
        pojo.setOneOfField(new BarType());


        Object asObjectfooType = pojo.getOneOfField();

        final GenericCasteable stillGotCasteable = pojo.getOneOfField(fallbackWithFooType(new ValueGetterFallback<FooType>() {
                    @Override
                    public void onGet(FooType value) {


                        //if its foo type we can get

                    }
                }),

                fallbackWithString(new ValueGetterFallback<String>() {

                                       @Override
                                       public void onGet(String value) {

                                           ///maybe it is a string ...
                                       }
                                   }

                ));




        stillGotCasteable.isNull();

        if(stillGotCasteable.isCompatibleWith(String.class)) {
            stillGotCasteable.asOf(String.class);
        }
    }


    @Test
    public void useCaseGetValuesV9() {
        SimplePojoV9 pojo = new SimplePojoV9();
        pojo.setOneOfField(new BarType());

        final GenericCasteable result = pojo.getOneOfField(); //this is safe however could return null


        if (result.isCompatibleWith(FooType.class)) {
            final FooType fooNotNull = result.asOf(FooType.class);

            System.out.println(fooNotNull.getFoo()); //not null
        }


        if (result.isNull()) {
            System.out.println("null");
        }

        try {
            result.asOf(FooType.class); //can throw exception
        } catch (IllegalArgumentException e) {
            //
        }


    }


    @Test
    public void useCaseGetValuesV10() {
        SimplePojoV10 pojo = new SimplePojoV10();
        pojo.setOneOfField(new BarType());

        final GeneratedCasteable result = pojo.getOneOfField(); //this is safe however could return null


        if (result.isFooType()) {
            final FooType fooNotNull = result.asFooType();

            System.out.println(fooNotNull.getFoo()); //not null
        }


        if (result.isNull()) {
            System.out.println("null");
        }

        try {
            result.asBarType(); //can throw exception
        } catch (IllegalArgumentException e) {
            //
        }


    }


    @Test
    public void useCaseGetValuesV7() {
        SimplePojoV7 pojo = new SimplePojoV7("empty string");


        if (pojo.isFooType()) //is of foo type
        {
            final FooType foo = pojo.getValueAsFoo();
            System.out.println(foo.getFoo()); //still can be null

        }

        pojo.getValueAsBar();//throws exception

    }


    /**
     * retrieved a single value eg. by setting appropriate headers
     * ,make sure that the header for choosing appropriate key is set
     */
    @Test
    public void useCaseSetAllValues() {
        SimplePojoV1 pojo = new SimplePojoV1();

        String data = "to specify only the value";

        pojo.setOneOfField(data);


        pojo.setOneOfField(new FooType());


    }
}
