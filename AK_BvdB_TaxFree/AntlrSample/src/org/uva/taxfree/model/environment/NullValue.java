package org.uva.taxfree.model.environment;

public class NullValue extends Value {
    @Override
    public String getValue() {
        return "";
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
        return true;
    }
}
