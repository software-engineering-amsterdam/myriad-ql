/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/evaluation/values/BooleanValue.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.formenvironment.values;

public class BooleanValue extends Value {

    private final Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
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
    public Value eq(Value valueArgument) {
        return new BooleanValue(value == valueArgument.getValue());
    }

    @Override
    public Value neq(Value valueArgument) {
        return new BooleanValue(value != valueArgument.getValue());
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
