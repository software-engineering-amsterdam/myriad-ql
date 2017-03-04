package org.ql.evaluator.value;

public abstract class Value {

    public Value lowerThanOrEqual(Value comparable) {
        return new UnknownValue();
    }

    public Value equal(Value comparable) {
        return new UnknownValue();
    }

    public Value or(Value or) {
        return new UnknownValue();
    }

    public abstract Object getPlainValue();
}
