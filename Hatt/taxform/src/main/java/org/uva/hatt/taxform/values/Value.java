package org.uva.hatt.taxform.values;

public abstract class Value<T> {

    public abstract T getValue();

    public Value add(Value val) {
        return null;
    }

    public Value divide(Value val) {
        return null;
    }

    public Value equal(Value val) {
        return null;
    }

    public Value greaterThan(Value val) {
        return null;
    }

    public Value greaterThanOrEqual(Value val) {
        return null;
    }

    public Value lessThan(Value val) {
        return null;
    }

    public Value lessThanOrEqual(Value val) {
        return null;
    }

    public Value and(Value val) {
        return null;
    }

    public Value or(Value val) {
        return null;
    }

    public Value multiply(Value val) {
        return null;
    }

    public Value notEqual(Value val) {
        return null;
    }

    public Value subtract(Value val) {
        return null;
    }

}
