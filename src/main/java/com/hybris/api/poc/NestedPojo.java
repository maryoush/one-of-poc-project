package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.hybris.api.poc.mapper.IsOneOfTypeField;

import java.util.Map;

public class NestedPojo {

    private String simpleField;

    @IsOneOfTypeField({String.class, Map.class})
    @JsonProperty(value = "other")
    private Object otherField;

    public String getSimple() {
        return simpleField;
    }

    public void setSimple(String simple) {
        this.simpleField = simple;
    }


    //################

    public <T> T getOther() throws ClassCastException {
        return (T) otherField;
    }

    public boolean hasOtherValue() {
        return otherField != null;
    }

    public boolean isOtherOf(final Class givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if (otherField == null) {
            return false;
        }
        return givenClazz.isAssignableFrom(otherField.getClass());
    }

    @JsonIgnore
    public void setOther(final String given) {
        this.otherField = given;
    }


    @JsonIgnore
    public void setOther(final Map given) {
        this.otherField = given;
    }
}