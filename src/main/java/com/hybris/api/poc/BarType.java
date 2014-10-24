package com.hybris.api.poc;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by i303813 on 16/10/14.
 */
//@JsonTypeName("BarType")
public class BarType {

    private String bar;

    private Number baz;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public Number getBaz() {
        return baz;
    }

    public void setBaz(Number baz) {
        this.baz = baz;
    }

}
