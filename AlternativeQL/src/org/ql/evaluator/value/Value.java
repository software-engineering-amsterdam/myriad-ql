package org.ql.evaluator.value;

public abstract class Value {

    public Value lowerThanOrEqual(Value comparable) {
        return new UnknownValue();
    }

    public Value lowerThanOrEqual(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value lowerThanOrEqual(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value lowerThan(Value comparable) {
        return new UnknownValue();
    }

    public Value lowerThan(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value lowerThan(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value greaterThan(Value comparable) {
        return new UnknownValue();
    }

    public Value greaterThan(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value greaterThan(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value greaterThanOrEqual(Value comparable) {
        return new UnknownValue();
    }

    public Value greaterThanOrEqual(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value greaterThanOrEqual(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value equal(Value comparable) {
        return new UnknownValue();
    }

    public Value equal(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value equal(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value product(Value product) {
        return new UnknownValue();
    }

    public Value product(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value product(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value increment() {
        return new UnknownValue();
    }

    public Value decrement() {
        return new UnknownValue();
    }

    public Value subtraction(Value subtraction) {
        return new UnknownValue();
    }

    public Value subtraction(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value subtraction(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value division(Value division) {
        return new UnknownValue();
    }

    public Value division(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value division(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value addition(Value addition) {
        return new UnknownValue();
    }

    public Value addition(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value addition(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value addition(StringValue comparable) {
        return new UnknownValue();
    }

    public Value notEqual(Value notEqual) {
        return new UnknownValue();
    }

    public Value notEqual(IntegerValue comparable) {
        return new UnknownValue();
    }

    public Value notEqual(DecimalValue comparable) {
        return new UnknownValue();
    }

    public Value negation() {
        return new UnknownValue();
    }

    public Value or(Value or) {
        return new UnknownValue();
    }

    public Value and(Value and) {
        return new UnknownValue();
    }

    public abstract Object getPlainValue();
}
