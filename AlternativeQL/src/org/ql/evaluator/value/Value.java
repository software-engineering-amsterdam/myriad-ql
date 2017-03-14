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

    public Value product(Value multiplicand) {
        return new UnknownValue();
    }

    public Value product(IntegerValue multiplier) {
        return new UnknownValue();
    }

    public Value product(DecimalValue multiplier) {
        return new UnknownValue();
    }

    public Value increment() {
        return new UnknownValue();
    }

    public Value decrement() {
        return new UnknownValue();
    }

    public Value subtraction(Value subtrahend) {
        return new UnknownValue();
    }

    public Value subtraction(IntegerValue minuend) {
        return new UnknownValue();
    }

    public Value subtraction(DecimalValue minuend) {
        return new UnknownValue();
    }

    public Value division(Value divisor) {
        return new UnknownValue();
    }

    public Value division(IntegerValue dividend) {
        return new UnknownValue();
    }

    public Value division(DecimalValue dividend) {
        return new UnknownValue();
    }

    public Value addition(Value augend) {
        return new UnknownValue();
    }

    public Value addition(IntegerValue addend) {
        return new UnknownValue();
    }

    public Value addition(DecimalValue addend) {
        return new UnknownValue();
    }

    public Value addition(StringValue addend) {
        return new UnknownValue();
    }

    public Value notEqual(Value comparable) {
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

    public Value or(Value value) {
        return new UnknownValue();
    }

    public Value and(Value value) {
        return new UnknownValue();
    }

    public boolean isUnknown() {
        return false;
    }

    public boolean isKnown() {
        return !isUnknown();
    }

    public String toString() {
        throw new AssertionError();
    }

    public boolean toBoolean() {
        throw new AssertionError();
    }

    public Double toDouble() {
        throw new AssertionError();
    }

    public Integer toInteger() {
        throw new AssertionError();
    }

    public abstract Object getPlainValue();

}
