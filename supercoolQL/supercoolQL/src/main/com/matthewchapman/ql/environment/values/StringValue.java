package com.matthewchapman.ql.environment.values;

/**
 * Created by matt on 18/03/2017.
 */
public class StringValue implements Value {

    private final String value;

    public StringValue(String input) {
        this.value = input;
    }

    @Override
    public String getTypeAsString() {
        return "STRING";
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Value equalTo(Value value) {
        return new BooleanValue(this.value.equals(value.getValue()));
    }

    @Override
    public Value notEqualTo(StringValue value) {
        return new BooleanValue(!this.value.equals(value.getValue()));
    }

    @Override
    public Value notEqualTo(Value value) {
        return value.notEqualTo(this);
    }
}
