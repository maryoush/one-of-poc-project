package com.hybris.api.poc;

import com.google.common.base.Preconditions;

public class SimplePojo {

    private String simpleField;

    private Object dummyField;

    private Object other;

    /**
     * Represents a possible type of property {@link com.hybris.api.poc.SimplePojo#dummyField}.
     */
    static enum DUMMYFIELD
    {
        /**
         * Possible one-of type {@link com.hybris.api.poc.FooType} for {@link SimplePojo#dummyField}
         */
        FOOTYPE(FooType.class),
        /**
         * Possible one-of type {@link com.hybris.api.poc.BarType} for {@link  SimplePojo#dummyField}
         */
        BARTYPE(BarType.class),
        /**
         * Possible one-of type {@link java.lang.String} for {@link  SimplePojo#dummyField}
         */
        STRING(String.class);

        private Class clazz;

        DUMMYFIELD(final Class givenClazz)
        {
            clazz = givenClazz;
        }

    }


    static enum OTHER
    {
        /**
         * Possible one-of type {@link com.hybris.api.poc.BarType} for {@link SimplePojo#other}
         */
        BARTYPE(BarType.class),
        /**
         * Possible one-of type {@link java.lang.Number} for {@link SimplePojo#dummyField}
         */
        NUMBER(Number.class);

        private Class clazz;

        OTHER(final Class givenClazz)
        {
            clazz = givenClazz;
        }

    }

    public String getSimpleField() {
        return simpleField;
    }

    public void setSimpleField(String simpleField) {
        this.simpleField = simpleField;
    }


    /**
     * A type not safe getter of the one of attribute
     *
     * <pre>
     *     {@code
     *
     *       "dummyField": {
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
     *  {@link com.hybris.api.poc.FooType }
     *  {@link com.hybris.api.poc.BarType}
     *  {@link java.lang.String}
     *
     * eg. if called as:
     * <pre>
     *     final FooType result = pojo.getValue();
     * </pre>
     *
     * in that cases it might turn out to throw an {@llink ClassCastException} if the current value
     * is not of typ of above
     *
     * It can be also called safely as
     * <code>
     *   final Object result = pojo.getValue();
     * </code>
     *
     * Remember this method still can yield a null.
     *
     *
     * requires of isOf call before, might cause ClassCast Exception
     *
     * @param <T> the expected type to be returned
     * @return a concrete of above types
     */
    public <T> T getDummyField() throws ClassCastException {
        return (T) dummyField;
    }

    /**
     * Provides a reference check.
     * Hint.
     * Use it for check if the #dummyField is null reference, which can not be
     * easily achieved with #isValueOf.
     *
     * It has the same result as:
     * <code>
     *     getDummyFieldValue()!=null;
     * </code>
     *
     *
     * @return true if the #dummyField is not null reference
     */
    public boolean hasDummyFieldValue() {
        return dummyField !=null;
    }

    /**
     * Checks whether the attribute of #dummyField is of given type.
     * Beware that it keeps the contract of the isInstanceOf construct, so in case it is null
     * none of parameters  will actually return true.
     * @param givenClazz a type to be checked against
     * @return true if the given type matches the exact value of #dummyField in sense of instance type
     * @throws java.lang.IllegalArgumentException if the passed givenClazz is null.
     */
    public boolean isDummyFieldOf(final DUMMYFIELD givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if(dummyField == null)
        {
            return false;
        }
        return  givenClazz.clazz.isAssignableFrom(dummyField.getClass());
    }

    /**
     * Type specific setter for #dummyField;
     * @param dummyField a reference to be set for #dummyField
     */
    public void setDummyField(final FooType dummyField) {
        this.dummyField = dummyField;
    }

    /**
     * Type specific setter for #dummyField;
     * @param dummyField a reference to be set for #dummyField
     */
    public void setDummyField(final BarType dummyField) {
        this.dummyField = dummyField;
    }

    /**
     * Type specific setter for #dummyField;
     * @param dummyField a reference to be set for #dummyField
     */
    public void setDummyField(final String dummyField) {
        this.dummyField = dummyField;
    }



    //################

    public <T> T getOther() throws ClassCastException {
        return (T) other;
    }

    public boolean hasOtherValue() {
        return other !=null;
    }

    public boolean isOtherOf(final OTHER givenClazz) throws IllegalArgumentException {
        Preconditions.checkArgument(givenClazz != null);
        if(other == null)
        {
            return false;
        }
        return  givenClazz.clazz.isAssignableFrom(other.getClass());
    }


    public void setOther(final FooType given) {
        this.other = given;
    }


    public void setOtherField(final BarType given) {
        this.other = given;
    }

    public void setOtherField(final String given) {
        this.other = given;
    }
}