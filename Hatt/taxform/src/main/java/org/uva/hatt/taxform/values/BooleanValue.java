package org.uva.hatt.taxform.values;

public class BooleanValue extends Value<Boolean> {

    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value == booleanValue.getValue());
    }

    @Override
    public BooleanValue and(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value && booleanValue.getValue());
    }

    @Override
    public BooleanValue or(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value || booleanValue.getValue());
    }

    @Override
    public BooleanValue notEqual(Value val) {
        BooleanValue booleanValue = ((BooleanValue) val);
        return new BooleanValue(value != booleanValue.getValue());
    }
}
