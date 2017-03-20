package org.uva.taxfree.model.environment;

public class BoolValue extends Value {
    private boolean mValue;

    public BoolValue(boolean value) {
        this.mValue = mValue;
    }

    @Override
    protected String getValue() {
        return String.valueOf(mValue);
    }

    @Override
    protected void setValue(int intValue) {
        UnsupportedValueException();
    }

    @Override
    protected void setValue(boolean boolValue) {
        mValue = boolValue;
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
