package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SimplePojoV4
{
    private Object genericReference;

	private transient Class clazz;

	@JsonCreator
	public SimplePojoV4(final String singleValue)
	{
        this.genericReference = singleValue;
        this.clazz = singleValue.getClass();
	}

	@JsonCreator
	public SimplePojoV4(final FooType fooType)
	{
		this.genericReference =fooType;
        this.clazz = fooType.getClass();
	}

    @JsonCreator
    public SimplePojoV4(final BarType barType)
    {
        this.genericReference =barType;
        this.clazz = barType.getClass();
    }


    /**
     * requires of isOf call before, might cause ClassCast Exception
     * @param <T> the expected type
     * @return
     */
	public <T> T getValue() throws ClassCastException
	{
		return (T) genericReference;
	}


    public boolean isNullOrOf(final Class givenClazz)
    {
        return isOf(givenClazz) || isNull();
    }

    public boolean isNull()
    {
        return genericReference == null; //can be a bit smarter
    }

    public boolean isOf(final Class givenClazz)
    {
        return clazz == givenClazz; //can be a bit smarter
    }

}