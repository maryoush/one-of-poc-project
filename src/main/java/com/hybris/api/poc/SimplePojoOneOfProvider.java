package com.hybris.api.poc;

import java.util.Map;
import java.util.Optional;

/**
 * Created by i303813 on 16/10/14.
 */
public interface SimplePojoOneOfProvider {

    /**
     * @return exact type or null is possible
     * @throws ClassCastException in case the exact value is not of BarType
     */
    BarType asBarType() throws  ClassCastException;

    /**
     *
     * @return sefa but ambiguous
     */
    Object asObject();

    /**
     *
     * @return optional of exact value if possible
     * @throws ClassCastException in case the exact value is not of expected type
     */
    <T> Optional<T> as(Class<T> type) throws ClassCastException;



    FooType asFooType() throws  ClassCastException;

    String asString() throws ClassCastException;

}
