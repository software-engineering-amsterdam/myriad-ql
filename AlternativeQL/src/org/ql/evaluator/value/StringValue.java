package org.ql.evaluator.value;


public class StringValue extends Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getPlainValue() {
        return value;
    }

    @Override
    public BooleanValue equal(Value comparable) {
        return new BooleanValue(value.equals(((StringValue) comparable).getPlainValue()));
    }

    @Override
    public BooleanValue notEqual(Value comparable) {
        return new BooleanValue(!value.equals(((StringValue) comparable).getPlainValue()));
    }

    @Override
    public Value addition(Value addition) {
        return addition.addition(this);
    }

    @Override
    public Value addition(StringValue comparable) {
        return new StringValue(comparable.value + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringValue that = (StringValue) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public String toString() {
        return getPlainValue();
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
