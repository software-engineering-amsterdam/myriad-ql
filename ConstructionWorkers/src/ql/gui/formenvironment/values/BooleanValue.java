/**
 * BooleanValue.java.
 */

package ql.gui.formenvironment.values;

public class BooleanValue extends Value {

    private final Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Value eq(Value valueArgument) {
        return new BooleanValue(value == valueArgument.getValue());
    }

    @Override
    public Value neq(Value valueArgument) {
        return new BooleanValue(value != valueArgument.getValue());
    }

    @Override
    public Value and(Value valueArgument) {
        return new BooleanValue(value && (Boolean) valueArgument.getValue());
    }

    @Override
    public Value or(Value valueArgument) {
        return new BooleanValue(value || (Boolean) valueArgument.getValue());
    }

    @Override
    public Value negation() {
        return new BooleanValue(!value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
