package org.uva.hatt.taxform.values;

public class StringValue extends Value {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
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

        StringValue stringValue = (StringValue) val;
        return new StringValue(value + stringValue.getValue());
    }

    @Override
    public BooleanValue equal(Value val) {
        StringValue stringValue = (StringValue) val;
        return new BooleanValue(value.equals(stringValue.getValue()));
    }

    @Override
    public BooleanValue notEqual(Value val) {
        StringValue stringValue = (StringValue) val;
        return new BooleanValue(!value.equals(stringValue.getValue()));
    }
}
