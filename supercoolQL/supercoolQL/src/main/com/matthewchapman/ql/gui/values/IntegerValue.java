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

    public Value add(IntegerValue value) {
        return new IntegerValue(this.value + value.getValue());
    }

    public Value subtract(IntegerValue value){
        return new IntegerValue(this.value - value.getValue());
    }

    public Value multiply(IntegerValue value){
        return new IntegerValue(this.value * value.getValue());
    }

    public Value divide(IntegerValue value) {
        return new IntegerValue(this.value / value.getValue());
    }

    public Value equalTo(IntegerValue value) {
        return new BooleanValue(this.value == value.getValue());
    }

    public Value inequal(IntegerValue value) { return new BooleanValue(this.value != value.getValue()); }

    public Value greaterThan(IntegerValue value) {
        return new BooleanValue(this.value > value.getValue());
    }

    public Value greaterThanEqualTo(IntegerValue value) {
        return new BooleanValue(this.value >= value.getValue());
    }

    public Value lessThan(IntegerValue value) {
        return new BooleanValue(this.value < value.getValue());
    }

    public Value lessThanEqualTo(IntegerValue value) {
        return new BooleanValue(this.value <= value.getValue());
    }

}
