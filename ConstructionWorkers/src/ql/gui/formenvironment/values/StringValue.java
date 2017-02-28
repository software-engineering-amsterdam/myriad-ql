package ql.gui.formenvironment.values;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class StringValue extends Value {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Value eq(Value valueArgument) {
        return new BooleanValue(value.equals((String) valueArgument.getValue()));
    }

    @Override
    public Value neq(Value valueArgument) {
        return new BooleanValue(!value.equals((String) valueArgument.getValue()));
    }

    @Override
    public Value gt(Value valueArgument) {
        return new BooleanValue(value.length() > ((String) valueArgument.getValue()).length());
    }

    @Override
    public Value gteq(Value valueArgument) {
        return new BooleanValue(value.length() >= ((String) valueArgument.getValue()).length());
    }

    @Override
    public Value lt(Value valueArgument) {
        return new BooleanValue(value.length() < ((String) valueArgument.getValue()).length());
    }

    @Override
    public Value lteq(Value valueArgument) {
        return new BooleanValue(value.length() <= ((String) valueArgument.getValue()).length());
    }
}
