package org.ql.evaluator.value;

import java.math.BigDecimal;

public class DecimalValue extends Value {

    private final BigDecimal value;

    public DecimalValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Value lowerThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) <= 0);
    }

    @Override
    public Value equal(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) == 0);
    }

    @Override
    public BooleanValue lowerThan(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) < 0);
    }

    @Override
    public BooleanValue greaterThan(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) > 0);
    }

    @Override
    public BooleanValue greaterThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) >= 0);
    }

    @Override
    public BooleanValue notEqual(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) != 0);
    }

    @Override
    public Value product(Value comparable) {
        return new DecimalValue(value.multiply((BigDecimal) comparable.getPlainValue()));
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
    public Value subtraction(Value subtraction) {
        return new DecimalValue(value.subtract((BigDecimal) subtraction.getPlainValue()));
    }

    public Value division(Value division) {
        return new DecimalValue(value.divide((BigDecimal) division.getPlainValue()));
    }

    @Override
    public Value addition(Value addition) {
        return new DecimalValue(value.add((BigDecimal) addition.getPlainValue()));
    }

    @Override
    public BigDecimal getPlainValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DecimalValue that = (DecimalValue) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
