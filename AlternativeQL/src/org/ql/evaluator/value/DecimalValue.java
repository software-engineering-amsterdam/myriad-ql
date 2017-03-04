package org.ql.evaluator.value;

import java.math.BigDecimal;

public class DecimalValue extends Value {

    private final BigDecimal value;

    public DecimalValue(BigDecimal value) {

        this.value = value;
    }

    @Override
    public Value lowerThanOrEqual(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) <= 0);
    }

    @Override
    public Value equal(Value comparable) {
        return new BooleanValue(value.compareTo((BigDecimal) comparable.getPlainValue()) == 0);
    }

    @Override
    public BigDecimal getPlainValue() {
        return value;
    }
}
