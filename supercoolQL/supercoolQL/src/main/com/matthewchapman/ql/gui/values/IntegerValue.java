package com.matthewchapman.ql.gui.values;

/**
 * Created by matt on 18/03/2017.
 */
public class IntegerValue extends Value {

    private final int value;

    public IntegerValue(int input) {
        this.value = input;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
