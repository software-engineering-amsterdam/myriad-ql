package org.uva.taxfree.model.environment;

public class IntValue extends Value {
    private int mInteger;

    public IntValue(int initialValue) {
        setValue(initialValue);
    }

    @Override
    public String getValue() {
        return String.valueOf(mInteger);
    }

    @Override
    public void setValue(int value) {
        mInteger = value;
    }

    @Override
    public void setValue(boolean boolValue) {
        UnsupportedValueException();
    }

    @Override
    public void setValue(String stringValue) {
        UnsupportedValueException();
    }

    @Override
    protected boolean isUninitialized() {
        return false;
    }
}
