package org.ql.evaluator.value;

import java.util.Objects;

public class IntegerValue extends Value {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Value equal(Value comparable) {
        return comparable.equal(this);
    }

    @Override
    public Value equal(IntegerValue comparable) {
        return new BooleanValue(value.equals(comparable.value));
    }

    @Override
    public Value lowerThanOrEqual(Value comparable) {
        return comparable.lowerThanOrEqual(this);
    }

    @Override
    public Value lowerThanOrEqual(IntegerValue comparable) {
        return new BooleanValue(comparable.value <= value);
    }

    @Override
    public Value lowerThan(Value comparable) {
        return comparable.lowerThan(this);
    }

    @Override
    public Value lowerThan(IntegerValue comparable) {
        return new BooleanValue(comparable.value < value);
    }

    @Override
    public Value greaterThan(Value comparable) {
        return comparable.greaterThan(this);
    }

    @Override
    public Value greaterThan(IntegerValue comparable) {
        return new BooleanValue(comparable.value > value);
    }

    @Override
    public Value greaterThanOrEqual(Value comparable) {
        return comparable.greaterThanOrEqual(this);
    }

    @Override
    public Value greaterThanOrEqual(IntegerValue comparable) {
        return new BooleanValue(comparable.value >= value);
    }

    @Override
    public Value notEqual(Value comparable) {
        return comparable.notEqual(this);
    }

    @Override
    public Value notEqual(IntegerValue comparable) {
        return new BooleanValue(!Objects.equals(comparable.value, value));
    }

    @Override
    public Value product(Value comparable) {
        return comparable.product(this);
    }

    @Override
    public Value product(IntegerValue comparable) {
        return new IntegerValue(comparable.value * value);
    }

    @Override
    public Value increment() {
        return new IntegerValue(value + 1);
    }

    @Override
    public Value decrement() {
        return new IntegerValue(value - 1);
    }

    @Override
    public Value subtraction(Value subtraction) {
        return subtraction.subtraction(this);
    }

    @Override
    public Value subtraction(IntegerValue comparable) {
        return new IntegerValue(comparable.value - value);
    }

    @Override
    public Value division(Value division) {
        return division.division(this);
    }

    @Override
    public Value division(IntegerValue comparable) {

        if (value == 0) {
            return new UnknownValue();
        }

        return new IntegerValue(comparable.value / value);
    }

    @Override
    public Value addition(Value addition) {
        return addition.addition(this);
    }

    @Override
    public Value addition(IntegerValue comparable) {
        return new IntegerValue(comparable.value + value);
    }

    @Override
    public Integer getPlainValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerValue that = (IntegerValue) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
