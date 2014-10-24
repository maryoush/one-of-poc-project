/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package com.hybris.api.poc.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybris.api.poc.jackson.CustomObjectMapperFactory;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * JaxRS layer custom object jaxrs resolver ...
 */
@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper;

    public ObjectMapperResolver() {
        mapper = CustomObjectMapperFactory.createMapper();
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }
}
