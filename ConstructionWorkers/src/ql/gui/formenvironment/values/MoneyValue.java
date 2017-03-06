/**
 * MoneyType.java.
 */

package ql.gui.formenvironment.values;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyValue extends Value {

    private final BigDecimal value;

    public MoneyValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Value addition(Value valueArgument) {
        return new MoneyValue(value.add((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public Value subtraction(Value valueArgument) {
        return new MoneyValue(value.subtract((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public Value division(Value valueArgument) {
        return new MoneyValue(value.divide((BigDecimal) valueArgument.getValue(), RoundingMode.HALF_UP));
    }

    @Override
    public Value multiplication(Value valueArgument) {
        return new MoneyValue(value.multiply((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public Value eq(Value valueArgument) {
        return new BooleanValue(value.equals(valueArgument.getValue()));
    }

    @Override
    public Value neq(Value valueArgument) {
        return new BooleanValue(!value.equals(valueArgument.getValue()));
    }

    @Override
    public Value gt(Value valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) > 0);
    }

    @Override
    public Value gteq(Value valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) >= 0);
    }

    @Override
    public Value lt(Value valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) < 0);
    }

    @Override
    public Value lteq(Value valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) <= 0);
    }

    @Override
    public Value negative() {
        return new MoneyValue(value.negate());
    }

    @Override
    public Value positive() {
        return new MoneyValue(value.abs());
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}