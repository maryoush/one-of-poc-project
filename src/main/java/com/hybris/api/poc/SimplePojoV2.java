package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SimplePojoV2
{

    private String stringType;
	private FooType fooType;
    private BarType barType;


	private transient Class clazz;

	@JsonCreator
	public SimplePojoV2(final String singleValue)
	{
        this.stringType = singleValue;
        this.clazz = singleValue.getClass();
	}

	@JsonCreator
	public SimplePojoV2(final FooType fooType)
	{
		this.fooType =fooType;
        this.clazz = fooType.getClass();
	}

    @JsonCreator
    public SimplePojoV2(final BarType barType)
    {
        this.barType =barType;
        this.clazz = barType.getClass();
    }


    public String getValueAsString()
    {
        return stringType;
    }

    public FooType getValueAsFoo()
    {
        return fooType;
    }

    public BarType getValueAsBar()
    {
        return barType;
    }


	public boolean isString()
	{
		return isOf(String.class);
	}

    public boolean isFooType()
    {
        return isOf(FooType.class);
    }

    public boolean isBarType()
    {
        return isOf(BarType.class);
    }

    private boolean isOf(final Class givenClazz)
    {
        return clazz == givenClazz;
    }
}