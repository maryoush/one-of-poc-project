package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.hybris.api.poc.model.IsOneOfTypeField;

public class SimplePojo {

    private String simpleField;

    @IsOneOfTypeField({FooType.class, BarType.class, String.class})
    @JsonProperty(value = "dummy")
    private Object dummyField;

    @IsOneOfTypeField({BarType.class, Number.class})
    @JsonProperty(value = "other")
    private Object otherField;

    public String getSimple() {
        return simpleField;
    }

    public void setSimple(String simple) {
        this.simpleField = simple;
    }


    /**
     * A type not safe getter of the one of attribute
     * <p/>
     * <pre>
     *     {@code
     *
     *       "dummy": {
     *              "type": "object",
     *                      "oneOf": [
     *                           { "$ref": "#/definitions/fooType" },
     *                           { "$ref": "#/definitions/barType" },
     *                           { "type": "string"}
     *                          ]
     *                      }
     *
     *
     *     }
     * </pre>
     * It can return one of the types :
     * {@link com.hybris.api.poc.FooType }
     * {@link com.hybris.api.poc.BarType}
     * {@link java.lang.String}
     * <p/>
     * eg. if called as:
     * <pre>
     *     final FooType result = pojo.getValue();
     * </pre>
     * <p/>
     * in that cases it might turn out to throw an {@llink ClassCastException} if the current value
     * is not of typ of above
     * <p/>
     * It can be also called safely as
     * <code>
     * final Object result = pojo.getValue();
     * </code>
     * <p/>
     * Remember this method still can yield a null.
     * <p/>
     * <p/>
     * requires of isOf call before, might cause ClassCast Exception
     *
     * @param <T> the expected type to be returned
     * @return a concrete of above types
     */
    public <T> T getDummy() throws ClassCastException {
        return (T) dummyField;
    }

    /**
     * Provides a reference check.
     * Hint.
     * Use it for check if the #dummy is null reference, which can not be
     * easily achieved with #isValueOf.
     * <p/>
     * It has the same result as:
     * <code>
     * getDummyValue()!=null;
     * </code>
     *
     * @return true if the #dummy is not null reference
     */
    public boolean hasDummyValue() {
        return dummyField != null;
    }

    /**
     * Checks whether the attribute of #dummy is of given type.
     * Beware that it keeps the contract of the isInstanceOf construct, so in case it is null
     * none of parameters  will actually return true.
     *
     * @param givenClazz a type to be checked against, expected values
     *                   <ul>
     *                   <li> {@link com.hybris.api.poc.FooType } </li>
     *                   <li> {@link com.hybris.api.poc.BarType} </li>
     *                   <li> {@link java.lang.String}</li>
     *                   </ul>
     *                   <p/>
     *                   according to JSON schema,
     *                   <pre>
     *                                                                                                                                                                                                           {@code
     *
     *                                                                                                                                                                                                             "dummy": {
     *                                                                                                                                                                                                                    "type": "object",
     *                                                                                                                                                                                                                            "oneOf": [
     *                                                                                                                                                                                                                                 { "$ref": "#/definitions/fooType" },
     *                                                                                                                                                                                                                                 { "$ref": "#/definitions/barType" },
     *                                                                                                                                                                                                                                 { "type": "string"}
     *                                                                                                                                                                                                                                ]
     *                                                                                                                                                                                                                            }
     *
     *
     *                                                                                                                                                                                                           }
     *                                                                                                                                                                                                       </pre>
     * @return true if the given type matches the exact value of #dummy in sense of instance type
     * @throws java.lang.IllegalArgumentException if the passed givenClazz is null.
     */
    public boolean isDummyOf(final Class givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if (dummyField == null) {
            return false;
        }
        return givenClazz.isAssignableFrom(dummyField.getClass());
    }

    /**
     * Type specific setter for #dummy;
     *
     * @param dummy a reference to be set for #dummy
     */
    @JsonIgnore
    public void setDummy(final FooType dummy) {
        this.dummyField = dummy;
    }

    /**
     * Type specific setter for #dummy;
     *
     * @param dummy a reference to be set for #dummy
     */
    @JsonIgnore
    public void setDummy(final BarType dummy) {
        this.dummyField = dummy;
    }

    /**
     * Type specific setter for #dummy;
     *
     * @param dummy a reference to be set for #dummy
     */
    @JsonIgnore
    public void setDummy(final String dummy) {
        this.dummyField = dummy;
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
    public void setOther(final BarType given) {
        this.otherField = given;
    }

    @JsonIgnore
    public void setOther(final Number given) {
        this.otherField = given;
    }

}