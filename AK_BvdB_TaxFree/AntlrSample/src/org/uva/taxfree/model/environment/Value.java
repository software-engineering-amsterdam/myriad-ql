package org.uva.taxfree.model.environment;

public abstract class Value {
    protected abstract String getValue();

    protected abstract void setValue(int intValue);

    protected abstract void setValue(boolean boolValue);

    protected abstract void setValue(String stringValue);

    protected abstract boolean isUninitialized();

    protected void UnsupportedValueException() {
        throw new RuntimeException("Value type is not supported on this value");
    }

}
