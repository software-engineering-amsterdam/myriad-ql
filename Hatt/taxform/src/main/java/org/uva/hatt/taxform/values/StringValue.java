package org.uva.hatt.taxform.values;

public class StringValue extends Value<String> {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Value add(Value val) {
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
