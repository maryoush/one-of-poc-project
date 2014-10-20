package com.hybris.api.poc;

/**
 * Created by i303813 on 16/10/14.
 */
public class SimplePojoV6 {


    private String simpleField;

    private Object oneOfField;

    public void setOneOfField(Object oneOfField) {
        this.oneOfField = oneOfField;
    }


    //this needs to be generated
    public static ValueGetterFallbackApplier<String> fallbackWithString(final ValueGetterFallback<String> fallback ){
        return new StringFallback(fallback);
    }

    public static ValueGetterFallbackApplier<FooType> fallbackWithFooType(final ValueGetterFallback<FooType> fallback ){
        return new FooTypeFallback(fallback);
    }



    public GenericCasteable getOneOfField(final ValueGetterFallbackApplier<?> ... fallbacks) {

        for(final ValueGetterFallbackApplier single : fallbacks)
        {
            single.process(oneOfField);
        }

        return GenericCasteable.of(oneOfField);

    }



    //this needs to be generated
    private static class StringFallback extends ValueGetterFallbackApplier<String> {

        private StringFallback(final ValueGetterFallback<String> fallback) {
            super(fallback);
        }

        @Override
        final protected Class<String> elementType() {
            return String.class;
        }
    }


    private static class FooTypeFallback extends ValueGetterFallbackApplier<FooType> {


        private FooTypeFallback(final ValueGetterFallback<FooType> fallback) {
            super(fallback);
        }

        @Override
        final protected Class<FooType> elementType() {
            return FooType.class;
        }
    }

    private static class BarTypeFallback extends ValueGetterFallbackApplier<BarType> {


        private BarTypeFallback(final ValueGetterFallback<BarType> fallback) {
            super(fallback);
        }

        @Override
        final protected Class<BarType> elementType() {
            return BarType.class;
        }
    }

    static abstract class ValueGetterFallbackApplier<T>  {

        private final ValueGetterFallback<T> fallback;

        protected ValueGetterFallbackApplier(ValueGetterFallback<T> fallback) {
            this.fallback = fallback;
        }


        protected abstract Class<T> elementType();

        final void process(final Object givenValue) {
            if (matches(givenValue)) {
                fallback.onGet((T) givenValue);
            }
        }


       private boolean matches(final Object givenValue) {
            if (givenValue == null) {
                return true;
            } else if (givenValue.getClass().isAssignableFrom(elementType())) {
                return true;
            } else
                return false;
        }


    }

    public static  interface ValueGetterFallback<T> {

        public void onGet(final T value);
    }


    public String getSimpleField() {
        return simpleField;
    }

    public void setSimpleField(String simpleField) {
        this.simpleField = simpleField;
    }

    public Object getOneOfField() {
        return oneOfField;
    }

}
