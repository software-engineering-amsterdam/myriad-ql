package org.ql.evaluator.value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalValue extends Value {

    private final BigDecimal value;

    public DecimalValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Value lowerThanOrEqual(Value comparable) {
        return comparable.lowerThanOrEqual(this);
    }

    @Override
    public Value lowerThanOrEqual(DecimalValue comparable) {
        return new BooleanValue(comparable.value.floatValue() <= value.floatValue());
    }

    @Override
    public Value equal(Value comparable) {
        return comparable.equal(this);
    }

    @Override
    public Value equal(DecimalValue comparable) {
        return new BooleanValue(comparable.value.equals(value));
    }

    @Override
    public Value lowerThan(Value comparable) {
        return comparable.lowerThan(this);
    }

    @Override
    public Value lowerThan(DecimalValue comparable) {
        return new BooleanValue(comparable.value.floatValue() < value.floatValue());
    }

    @Override
    public Value greaterThan(Value comparable) {
        return comparable.greaterThan(this);
    }

    @Override
    public Value greaterThan(DecimalValue comparable) {
        return new BooleanValue(comparable.value.floatValue() > value.floatValue());
    }

    @Override
    public Value greaterThanOrEqual(Value comparable) {
        return comparable.greaterThanOrEqual(this);
    }

    @Override
    public Value greaterThanOrEqual(DecimalValue comparable) {
        return new BooleanValue(comparable.value.floatValue() >= value.floatValue());
    }

    @Override
    public Value notEqual(Value comparable) {
        return comparable.notEqual(this);
    }

    @Override
    public Value notEqual(DecimalValue comparable) {
        return new BooleanValue(comparable.value.floatValue() != value.floatValue());
    }

    @Override
    public Value product(Value multiplicand) {
        return multiplicand.product(this);
    }

    @Override
    public Value product(DecimalValue multiplier) {
        return new DecimalValue(multiplier.value.multiply(value));
    }

    @Override
    public Value increment() {
        return new DecimalValue(value.add(new BigDecimal(1)));
    }

    @Override
    public Value decrement() {
        return new DecimalValue(value.subtract(new BigDecimal(1)));
    }

    @Override
    public Value subtraction(Value subtrahend) {
        return subtrahend.subtraction(this);
    }

    @Override
    public Value subtraction(DecimalValue minuend) {
        return new DecimalValue(minuend.value.subtract(value));
    }

    public Value division(Value divisor) {
        return divisor.division(this);
    }

    public Value division(DecimalValue dividend) {
        if (value.floatValue() == 0) {
            return new UnknownValue();
        }

        return new DecimalValue(dividend.value.divide(value, RoundingMode.UNNECESSARY));
    }

    @Override
    public Value addition(Value augend) {
        return augend.addition(this);
    }

    @Override
    public Value addition(DecimalValue addend) {
        return new DecimalValue(addend.value.add(value));
    }

    @Override
    public BigDecimal getPlainValue() {
        return value;
    }

    @Override
    public Double toDouble() {
        return value.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecimalValue that = (DecimalValue) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public String toString() {
        return value.toPlainString();
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
