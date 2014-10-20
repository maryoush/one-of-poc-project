package com.hybris.api.poc;

/**
 * Generic casteable
 */
public class GeneratedCasteable {

    private final GenericCasteable value;

    private GeneratedCasteable(final Object given) {
        this.value = GenericCasteable.of(given);
    }

    public static GeneratedCasteable of(final Object o) {
        return new GeneratedCasteable(o);
    }

    /**
     * Assumes the null is always compatible
     *
     * @return
     */
    public boolean isString() {
        return value.isCompatibleWith(String.class);
    }

    public boolean isFooType() {
        return value.isCompatibleWith(FooType.class);
    }

    public boolean isBarType() {
        return value.isCompatibleWith(BarType.class);
    }


    public String asString() throws IllegalArgumentException {
        return (String) value.asOf(String.class);
    }


    public FooType asFooType() throws IllegalArgumentException {
        return (FooType) value.asOf(FooType.class);
    }


    public BarType asBarType() throws IllegalArgumentException {
        return (BarType) value.asOf(BarType.class);
    }


    public boolean isNull() {
        return !value.isNull();
    }
}
