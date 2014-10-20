package com.hybris.api.poc;

/**
 * Created by i303813 on 16/10/14.
 */
public class SimplePojoV9 {


    private String simpleField;


    private Object oneOfField;


    public GenericCasteable getOneOfField() {
        return GenericCasteable.of(oneOfField);
    }


    public String getSimpleField() {
        return simpleField;
    }

    public void setSimpleField(String simpleField) {
        this.simpleField = simpleField;
    }


    public void setOneOfField(Object oneOfField) {
        this.oneOfField = oneOfField;
    }
}
