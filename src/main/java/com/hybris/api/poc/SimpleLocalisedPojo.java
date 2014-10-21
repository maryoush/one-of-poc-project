package com.hybris.api.poc;

import com.google.common.base.Preconditions;

import java.util.Map;

/**
 * Created by i303813 on 16/10/14.
 */
public class SimpleLocalisedPojo {


    private Object localizedProperty;

    static enum LOCALIZEDPROPERTY
    {

        MAP(Map.class),

        STRING(String.class);

        private Class clazz;

        LOCALIZEDPROPERTY(final Class givenClazz)
        {
            clazz = givenClazz;
        }

    }


    public <T> T getLocalizedProperty() throws ClassCastException {
        return (T) localizedProperty;
    }


    public boolean hasLocalizedPropertyValue() {
        return localizedProperty != null;
    }

    public boolean isLocalizedPropertyOf(final LOCALIZEDPROPERTY givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if (localizedProperty == null) {
            return false;
        }
        return givenClazz.clazz.isAssignableFrom(localizedProperty.getClass());
    }

    public void setLocalizedProperty(final String localizedProperty) {
        this.localizedProperty = localizedProperty;
    }


    public void setLocalizedProperty(final Map localizedProperty) {
        this.localizedProperty = localizedProperty;
    }
}
