package com.hybris.api.poc;

import com.google.common.base.Preconditions;

import java.util.Optional;

/**
 * Generic casteable
 */
public class GenericCasteable {

    private final Optional<?> value;

    private GenericCasteable(final Object given) {
        this.value = Optional.ofNullable(given);
    }

    public static GenericCasteable of(final Object o) {
        return new GenericCasteable(o);
    }

    /**
     * Assumes the null is always compatible
     *
     * @param givenClass
     * @return
     */
    public boolean isCompatibleWith(final Class<?> givenClass) {
        Preconditions.checkNotNull(givenClass);
        return value.isPresent() ? value.get().getClass() == givenClass : true;
    }

    /**
     *
     * @param expected the class of object to be retrieved
     * @param <T>
     * @return the object instance
     * @throws IllegalArgumentException in case the object is of different type
     */
    public <T> T asOf(final Class<T> expected) throws IllegalArgumentException {
        if (isCompatibleWith(expected)) {
            return (T) value.get();
        } else {
            throw new IllegalArgumentException("types not compatible expected "+expected+" got "+value.get());
        }
    }

    /**
     * checks whether value was set
     * @return
     */
    public boolean isNull() {
        return !value.isPresent();
    }
}
