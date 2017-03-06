/**
 * IntegerValue.java.
 */

package ql.gui.formenvironment.values;

public class IntegerValue extends Value {

    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Value addition(Value valueArgument) {
        return new IntegerValue(value + (Integer) valueArgument.getValue());
    }

    @Override
    public Value subtraction(Value valueArgument) {
        return new IntegerValue(value - (Integer) valueArgument.getValue());
    }

    @Override
    public Value division(Value valueArgument) {
        return new IntegerValue(value / (Integer) valueArgument.getValue());
    }

    @Override
    public Value multiplication(Value valueArgument) {
        return new IntegerValue(value * (Integer) valueArgument.getValue());
    }

    @Override
    public Value eq(Value valueArgument) {
        return new BooleanValue(value.equals(valueArgument.getValue()));
    }

    @Override
    public Value neq(Value valueArgument) {
        return new BooleanValue(value.equals(valueArgument.getValue()));
    }

    @Override
    public Value gt(Value valueArgument) {
        return new BooleanValue(value > (Integer) valueArgument.getValue());
    }

    @Override
    public Value gteq(Value valueArgument) {
        return new BooleanValue(value >= (Integer) valueArgument.getValue());
    }

    @Override
    public Value lt(Value valueArgument) {
        return new BooleanValue(value < (Integer) valueArgument.getValue());
    }

    @Override
    public Value lteq(Value valueArgument) {
        return new BooleanValue(value <= (Integer) valueArgument.getValue());
    }

    @Override
    public Value negative() {
        return new IntegerValue(-value);
    }

    @Override
    public Value positive() {
        return new IntegerValue(Math.abs(value));
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
