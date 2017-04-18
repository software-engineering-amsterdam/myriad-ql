package org.uva.hatt.taxform.values;

public abstract class Value {

    public abstract Object getValue();

    public abstract boolean isUndefined();

    public Value add(Value val) {
        return new Undefined();
    }

    public Value divide(Value val) {
        return new Undefined();
    }

    public Value equal(Value val) {
        return new Undefined();
    }

    public Value greaterThan(Value val) {
        return new Undefined();
    }

    public Value greaterThanOrEqual(Value val) {
        return new Undefined();
    }

    public Value lessThan(Value val) {
        return new Undefined();
    }

    public Value lessThanOrEqual(Value val) {
        return new Undefined();
    }

    public Value and(Value val) {
        return new Undefined();
    }

    public Value or(Value val) {
        return new Undefined();
    }

    public Value multiply(Value val) {
        return new Undefined();
    }

    public Value notEqual(Value val) {
        return new Undefined();
    }

    public Value subtract(Value val) {
        return new Undefined();
    }

}
