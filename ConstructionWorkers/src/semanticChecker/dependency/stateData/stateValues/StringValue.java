package semanticChecker.dependency.stateData.stateValues;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class StringValue extends StateValue{

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public StateValue eq(StateValue valueArgument) {
        return new BooleanValue(value.equals((String) valueArgument.getValue()));
    }

    @Override
    public StateValue neq(StateValue valueArgument) {
        return new BooleanValue(!value.equals((String) valueArgument.getValue()));
    }

    @Override
    public StateValue gt(StateValue valueArgument) {
        return new BooleanValue(value.length() > ((String) valueArgument.getValue()).length());
    }

    @Override
    public StateValue gteq(StateValue valueArgument) {
        return new BooleanValue(value.length() >= ((String) valueArgument.getValue()).length());
    }

    @Override
    public StateValue lt(StateValue valueArgument) {
        return new BooleanValue(value.length() < ((String) valueArgument.getValue()).length());
    }

    @Override
    public StateValue lteq(StateValue valueArgument) {
        return new BooleanValue(value.length() <= ((String) valueArgument.getValue()).length());
    }
}
