package org.ql.evaluator.value;

public class BooleanValue extends Value {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public BooleanValue or(Value or) {
        return new BooleanValue(value || ((BooleanValue) or).getPlainValue());
    }

    public Boolean getPlainValue() {
        return value;
    }
}
