package org.uva.taxfree.ql.model.values;

public class IntValue extends Value {

    private final int mValue;

    public IntValue(int value) {
        mValue = value;
    }

    @Override
    public IntValue multiply(Value value) {
        return value.multiply(this);
    }

    @Override
    protected IntValue multiply(IntValue intValue) {
        return new IntValue(intValue.mValue * mValue);
    }

    @Override
    public IntValue divide(Value value) {
        return value.divide(this);
    }

    @Override
    protected IntValue divide(IntValue intValue) {
        return 0 != mValue ? new IntValue(intValue.mValue / mValue) : new IntValue(0);
    }

    @Override
    public IntValue add(Value value) {
        return value.add(this);
    }

    @Override
    protected IntValue add(IntValue intValue) {
        return new IntValue(intValue.mValue + mValue);
    }

    @Override
    public Value subtract(Value value) {
        return value.subtract(this);
    }

    @Override
    protected IntValue subtract(IntValue intValue) {
        return new IntValue(intValue.mValue - mValue);
    }

    @Override
    public BooleanValue lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    protected BooleanValue lessThan(IntValue intValue) {
        return new BooleanValue(intValue.mValue < mValue);
    }

    @Override
    public BooleanValue lessEqualThan(Value value) {
        return value.lessEqualThan(this);
    }

    @Override
    protected BooleanValue lessEqualThan(IntValue intValue) {
        return new BooleanValue(intValue.mValue <= mValue);
    }

    @Override
    public BooleanValue greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    protected BooleanValue greaterThan(IntValue intValue) {
        return new BooleanValue(intValue.mValue > mValue);
    }

    @Override
    public BooleanValue greaterEqualThan(Value value) {
        return value.greaterEqualThan(this);
    }

    @Override
    protected BooleanValue greaterEqualThan(IntValue intValue) {
        return new BooleanValue(intValue.mValue >= mValue);
    }

    @Override
    public BooleanValue equalTo(Value value) {
        return value.equalTo(this);
    }

    @Override
    protected BooleanValue equalTo(IntValue intValue) {
        return new BooleanValue(this.equalsToValue(intValue));
    }

    @Override
    public BooleanValue notEqualTo(Value value) {
        return value.notEqualTo(this);
    }

    @Override
    protected BooleanValue notEqualTo(IntValue intValue) {
        return new BooleanValue(!this.equalsToValue(intValue));
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
        return false;
    }

    @Override
    protected boolean equalsToValue(IntValue intValue) {
        return intValue.mValue == mValue;
    }

    @Override
    protected boolean equalsToValue(StringValue StringValue) {
        return false;
    }
}
