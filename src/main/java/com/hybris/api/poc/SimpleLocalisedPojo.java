package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.hybris.api.poc.model.IsOneOfTypeField;

import java.util.Map;

public class SimpleLocalisedPojo {


    @IsOneOfTypeField({String.class, Map.class})
    @JsonProperty(value = "localisedProperty")
    private Object localisedPropertyField;

    //################

    public <T> T getLocalisedProperty() throws ClassCastException {
        return (T) localisedPropertyField;
    }

    public boolean hasLocalisedPropertyValue() {
        return localisedPropertyField != null;
    }

    public boolean isLocalisedPropertyOf(final Class givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if (localisedPropertyField == null) {
            return false;
        }
        return givenClazz.isAssignableFrom(localisedPropertyField.getClass());
    }


    @JsonIgnore
    public void setLocalisedProperty(final String given) {
        this.localisedPropertyField = given;
    }

    @JsonIgnore
    public void setLocalisedProperty(final Map given) {
        this.localisedPropertyField = given;
    }

}