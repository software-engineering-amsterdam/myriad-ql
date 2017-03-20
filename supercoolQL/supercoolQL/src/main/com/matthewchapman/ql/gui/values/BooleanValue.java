package com.matthewchapman.ql.gui.values;

/**
 * Created by matt on 18/03/2017.
 */

public class BooleanValue extends Value {

    private final boolean value;

    public BooleanValue(boolean input) {
        this.value = input;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    public Value equalTo(Value value) {
        return new BooleanValue(this.equals(value));
    }

    public Value inequal(Value value) {
        return new BooleanValue(!this.equals(value));
    }

    public Value logicalAnd(BooleanValue value) {
        return new BooleanValue(this.value && value.getValue());
    }

    public Value logicalOr(BooleanValue value) {
        return new BooleanValue(this.value || value.getValue());
    }

    public Value negate(BooleanValue value) {
        return new BooleanValue(!value.getValue());
    }
}
