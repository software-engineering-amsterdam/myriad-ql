package org.uva.taxfree.ql.model.values;

public class BooleanValue extends Value {

    private final boolean mValue;

    public BooleanValue(boolean value) {
        mValue = value;
    }

    @Override
    public BooleanValue equalTo(Value value) {
        return value.equalTo(this);
    }

    @Override
    protected BooleanValue equalTo(BooleanValue booleanValue) {
        return new BooleanValue(this.equalsToValue(booleanValue));
    }

    @Override
    public BooleanValue notEqualTo(Value value) {
        return value.notEqualTo(this);
    }

    @Override
    protected BooleanValue notEqualTo(BooleanValue booleanValue) {
        return new BooleanValue(!this.equalsToValue(booleanValue));
    }

    @Override
    public BooleanValue logicalAnd(Value value) {
        return value.logicalAnd(this);
    }

    @Override
    protected BooleanValue logicalAnd(BooleanValue booleanValue) {
        return new BooleanValue(booleanValue.mValue && mValue);
    }

    @Override
    public BooleanValue logicalOr(Value value) {
        return value.logicalOr(this);
    }

    @Override
    protected BooleanValue logicalOr(BooleanValue booleanValue) {
        return new BooleanValue(booleanValue.mValue || mValue);
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }

    @Override
    public boolean equalsToValue(Value value) {
        return value.equalsToValue(this);
    }

    @Override
    protected boolean equalsToValue(BooleanValue booleanValue) {
        return booleanValue.mValue == mValue;
    }

    @Override
    protected boolean equalsToValue(IntValue intValue) {
        return false;
    }

    @Override
    protected boolean equalsToValue(StringValue StringValue) {
        return false;
    }
}
