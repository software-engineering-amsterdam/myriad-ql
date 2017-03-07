package org.ql.evaluator.value;

public class BooleanValue extends Value {
    private boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public BooleanValue or(Value or) {
        return new BooleanValue(value || ((BooleanValue) or).getPlainValue());
    }

    @Override
    public BooleanValue and(Value and) {
        return new BooleanValue(value && ((BooleanValue) and).getPlainValue());
    }

    @Override
    public BooleanValue notEqual(Value comparable) {
        return new BooleanValue(value != (Boolean) comparable.getPlainValue());
    }

    @Override
    public BooleanValue equal(Value comparable) {
        return new BooleanValue(value == (Boolean) comparable.getPlainValue());
    }

    @Override
    public BooleanValue negation() {
        return new BooleanValue(!value);
    }

    public Boolean getPlainValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanValue that = (BooleanValue) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
