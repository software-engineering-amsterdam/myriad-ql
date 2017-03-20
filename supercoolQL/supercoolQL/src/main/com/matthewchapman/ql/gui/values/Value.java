package com.matthewchapman.ql.gui.values;

/**
 * Created by matt on 18/03/2017.
 */
public abstract class Value {

    public abstract Object getValue();

    public Value add(Value value) {
        return new NullValue();
    }

    public Value subtract(Value value){
        return new NullValue();
    }

    public Value multiply(Value value){
        return new NullValue();
    }

    public Value divide(Value value) {
        return new NullValue();
    }

    public Value equalTo(Value value) {
        return new NullValue();
    }

    public Value inequal(Value value) {
        return new NullValue();
    }

    public Value greaterThan(Value value) {
        return new NullValue();
    }

    public Value greaterThanEqualTo(Value value) {
        return new NullValue();
    }

    public Value lessThan(Value value) {
        return new NullValue();
    }

    public Value lessThanEqualTo(Value value) {
        return new NullValue();
    }

    public Value logicalAnd(Value value) {
        return new NullValue();
    }

    public Value logicalOr(Value value) {
        return new NullValue();
    }

    public Value negate(Value value) {
        return new NullValue();
    }
}
