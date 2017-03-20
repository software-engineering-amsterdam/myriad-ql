package com.matthewchapman.ql.gui.values;

/**
 * Created by matt on 18/03/2017.
 */
public class StringValue extends Value {

    private final String value;

    public StringValue(String input) {
        this.value = input;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
