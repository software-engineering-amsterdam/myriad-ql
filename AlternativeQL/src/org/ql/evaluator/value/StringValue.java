package org.ql.evaluator.value;

public class StringValue extends Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getPlainValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value comparable) {
        return new BooleanValue(value.equals(((StringValue) comparable).getPlainValue()));
    }

    @Override
    public BooleanValue notEqual(Value comparable) {
        return new BooleanValue(!value.equals(((StringValue) comparable).getPlainValue()));
    }
}
