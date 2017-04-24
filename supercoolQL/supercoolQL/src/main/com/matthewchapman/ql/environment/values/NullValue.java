package com.matthewchapman.ql.environment.values;

/**
 * Created by matt on 18/03/2017.
 */
public class NullValue implements Value {

    private final Void value;

    public NullValue() {
        this.value = null;
    }

    @Override
    public String getTypeAsString() {
        return "NULL_VALUE";
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
