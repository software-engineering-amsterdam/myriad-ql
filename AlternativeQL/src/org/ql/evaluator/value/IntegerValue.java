package org.ql.evaluator.value;

public class IntegerValue extends Value {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public BooleanValue equal(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) == 0);
    }

    @Override
    public BooleanValue lowerThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((Integer) comparable.getPlainValue()) <= 0);
    }

    @Override
    public Integer getPlainValue() {
        return value;
    }
}
