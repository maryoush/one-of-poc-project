package com.hybris.api.poc;

import com.google.common.base.Preconditions;

/**
 * Created by i303813 on 16/10/14.
 */
public class IsOneOfUtil {

    //kind of stupid
    public static boolean isOf(final Object givenPojo, final Class clazz) {
        Preconditions.checkNotNull(clazz);
        return givenPojo.getClass() == clazz;
    }

    //kind of dangerous
    public static <T> T asOf(final Object givenPojo) throws ClassCastException {

        return (T) givenPojo;
    }

    public static GenericCasteable asCasteable(final Object givenPojo) {
        return GenericCasteable.of(givenPojo);
    }
}
