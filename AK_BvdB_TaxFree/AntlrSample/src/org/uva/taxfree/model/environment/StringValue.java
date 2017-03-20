package org.uva.taxfree.model.environment;

public class StringValue extends Value {
    private String mStringValue;

    public StringValue(String stringValue) {
        mStringValue = stringValue;
    }

    @Override
    protected String getValue() {
        return mStringValue;
    }

    @Override
    protected void setValue(int intValue) {
        UnsupportedValueException();
    }

    @Override
    protected void setValue(boolean boolValue) {
        UnsupportedValueException();
    }

    @Override
    protected void setValue(String stringValue) {
        UnsupportedValueException();
    }

    @Override
    protected boolean isUninitialized() {
        return false;
    }
}
