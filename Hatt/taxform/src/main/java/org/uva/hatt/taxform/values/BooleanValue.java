package org.uva.hatt.taxform.values;

public class BooleanValue extends Value {

    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value == booleanValue.isValue());
    }

    @Override
    public BooleanValue and(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value && booleanValue.isValue());
    }

    @Override
    public BooleanValue or(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value || booleanValue.isValue());
    }

    @Override
    public BooleanValue notEqual(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value != booleanValue.isValue());
    }
}
