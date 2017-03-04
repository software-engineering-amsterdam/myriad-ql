package org.ql.evaluator.value;

public class IntegerValue extends Value {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public BooleanValue equal(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) == 0);
    }

    @Override
    public BooleanValue lowerThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) <= 0);
    }

    @Override
    public BooleanValue lowerThan(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) < 0);
    }

    @Override
    public BooleanValue greaterThan(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) > 0);
    }

    @Override
    public BooleanValue greaterThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) >= 0);
    }

    @Override
    public BooleanValue notEqual(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) != 0);
    }

    @Override
    public Value product(Value comparable) {
        return new IntegerValue(value * ((Integer) comparable.getPlainValue()));
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
        return new IntegerValue(value - (Integer) subtraction.getPlainValue());
    }

    public Value division(Value division) {
        return new IntegerValue(value / (Integer) division.getPlainValue());
    }

    @Override
    public Value addition(Value addition) {
        return new IntegerValue(value + (Integer) addition.getPlainValue());
    }

    @Override
    public Integer getPlainValue() {
        return value;
    }
}
