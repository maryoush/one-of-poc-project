package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SimplePojoV7 {

    private static final Getter<Object> NOT_INITIALIZED = new Getter<Object>() {
        @Override
        public Object get() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Class<Object> type() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean initialized() {
            return false;
        }
    };

    private Getter<?> stringType = NOT_INITIALIZED;
    private Getter<?> fooType = NOT_INITIALIZED;
    private Getter<?> barType = NOT_INITIALIZED;


    private interface Getter<T> {
        T get();

        Class<T> type();

        boolean initialized();
    }

    private  static <T> Getter<T> as(final T value) {
        return new Getter<T>() {
            @Override
            public T get() {
                return value;
            }

            @Override
            public Class<T> type() {
                return (Class<T>) value.getClass();
            }

            @Override
            public boolean initialized() {
                return true;
            }
        };
    }


    @JsonCreator
    public SimplePojoV7(final String singleValue) {
        this.stringType = as(singleValue);
    }

    @JsonCreator
    public SimplePojoV7(final FooType fooType) {
        this.fooType = as(fooType);
    }

    @JsonCreator
    public SimplePojoV7(final BarType barType) {
        this.barType = as(barType);
    }

    /**
     * requires the isString check
     *
     * @return exact value
     * @throws  IllegalArgumentException if is of type string
     */
    public String getValueAsString() throws IllegalArgumentException
    {

        if (!stringType.initialized()) {
            throw new IllegalStateException();
        }
        return (String) stringType.get();
    }

    public FooType getValueAsFoo() throws IllegalArgumentException{
        if (!fooType.initialized()) {
            throw new IllegalStateException();
        }
        return (FooType) fooType.get();
    }

    public BarType getValueAsBar() throws IllegalArgumentException {
        if (!barType.initialized()) {
            throw new IllegalStateException();
        }
        return (BarType) barType.get();
    }


    public boolean isString() {
        return stringType.initialized();
    }

    public boolean isFooType() {
        return fooType.initialized();
    }

    public boolean isBarType() {
        return barType.initialized();
    }

}