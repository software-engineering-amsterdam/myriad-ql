package org.uva.hatt.taxform.values;

public class IntegerValue extends Value {

    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public boolean isUndefined() {
        return false;
    }

    @Override
    public Value add(Value val) {
        if (val.isUndefined()) {
            return new Undefined();
        }

        IntegerValue integerValue = (IntegerValue) val;
        return new IntegerValue(value + integerValue.getValue());
    }

    @Override
    public Value divide(Value val) {
        if (val.isUndefined()) {
            return new Undefined();
        }

        IntegerValue integerValue = (IntegerValue) val;

        if (integerValue.getValue() == 0) {
            return new IntegerValue(0);
        }

        return new IntegerValue(value / integerValue.getValue());
    }

    @Override
    public BooleanValue equal(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value == integerValue.getValue());
    }

    @Override
    public BooleanValue greaterThan(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value > integerValue.getValue());
    }

    @Override
    public BooleanValue greaterThanOrEqual(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value >= integerValue.getValue());
    }

    @Override
    public BooleanValue lessThan(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value < integerValue.getValue());
    }

    @Override
    public BooleanValue lessThanOrEqual(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value <= integerValue.getValue());
    }

    @Override
    public Value multiply(Value val) {
        if (val.isUndefined()) {
            return new Undefined();
        }

        IntegerValue integerValue = (IntegerValue) val;
        return new IntegerValue(value * integerValue.getValue());
    }

    @Override
    public BooleanValue notEqual(Value val) {
        IntegerValue integerValue = (IntegerValue) val;
        return new BooleanValue(value != integerValue.getValue());
    }

    @Override
    public Value subtract(Value val) {
        if (val.isUndefined()) {
            return new Undefined();
        }

        IntegerValue integerValue = (IntegerValue) val;
        return new IntegerValue(value - integerValue.getValue());
    }
}
