package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by i303813 on 16/10/14.
 */
//@JsonTypeName("FooType")
public class FooType {

    private Object foo;

    public Object getFoo() {
        return foo;
    }

    public void setFoo(Object foo) {
        this.foo = foo;
    }



}
