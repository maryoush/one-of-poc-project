package com.hybris.api.poc;

import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Created by i303813 on 16/10/14.
 */
public class SimplePojoV3 {

    private String simpleField;


    private Object oneOfField;

    public Object getOneOfFieldAsObject() {
        return oneOfField;
    }

    public <T> Optional<T> getOneOfFieldAs(final Class<T> type) throws ClassCastException {
        Preconditions.checkNotNull(type);
        if(oneOfField == null){
            return Optional.empty();
        }
        else
        {
            if(type.isAssignableFrom( oneOfField.getClass() ))
            {
                return Optional.of((T) oneOfField);
            }
            throw new ClassCastException("the current value "+oneOfField+" can not be casted to type :"+type);
        }
    }


    /**
     * <pre>
     *
     *     {@code
     *      "oneOfField": {
     * "type": "object",
     * "oneOf": [
     * { "$ref": "#/definitions/fooType" },
     * { "$ref": "#/definitions/barType" },
     * { "type": "string"}
     * ]
     * }
     *
     *     }
     *
     *
     * </pre>
     *
     * @param oneOfField
     */
    public void setOneOfField(final FooType oneOfField) {
        this.oneOfField = oneOfField;
    }

    public void setOneOfField(final BarType oneOfField) {
        this.oneOfField = oneOfField;
    }

    public void setOneOfField(final String oneOfField) {
        this.oneOfField = oneOfField;
    }


    public String getSimpleField() {
        return simpleField;
    }

    /**
     * <pre>
     *     {@code
     *       "simpleField": {
     * "type": "string"
     * },
     *
     *
     *     }
     * </pre>
     *
     * @param simpleField
     */
    public void setSimpleField(String simpleField) {
        this.simpleField = simpleField;
    }
}
