package org.uva.taxfree.ql.model.values;

public class StringValue extends Value {

    private final String mValue;

    public StringValue(String value) {
        mValue = value;
    }

    @Override
    public BooleanValue equalTo(Value value) {
        return value.equalTo(this);
    }

    @Override
    protected BooleanValue equalTo(StringValue stringValue) {
        return new BooleanValue(this.equalsToValue(stringValue));
    }

    @Override
    public BooleanValue notEqualTo(Value value) {
        return value.notEqualTo(this);
    }

    @Override
    protected BooleanValue notEqualTo(StringValue stringValue) {
        return new BooleanValue(!this.equalsToValue(stringValue));
    }

    @Override
    public String toString() {
        return mValue;
    }

    @Override
    public boolean equalsToValue(Value value) {
        return value.equalsToValue(this);
    }

    @Override
    protected boolean equalsToValue(BooleanValue booleanValue) {
        return false;
    }

    @Override
    protected boolean equalsToValue(IntValue intValue) {
        return false;
    }

    @Override
    protected boolean equalsToValue(StringValue stringValue) {
        return stringValue.mValue.equals(mValue);
    }
}
