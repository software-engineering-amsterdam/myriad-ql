package semanticChecker.dependency.stateData.stateValues;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class BooleanValue extends StateValue {

    private final Boolean value;

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public StateValue eq(StateValue valueArgument) {
        return new BooleanValue(value == (Boolean) valueArgument.getValue());
    }

    @Override
    public StateValue neq(StateValue valueArgument) {
        return new BooleanValue(value != (Boolean) valueArgument.getValue());
    }

    @Override
    public StateValue and(StateValue valueArgument) {
        return new BooleanValue(value && (Boolean) valueArgument.getValue());
    }

    @Override
    public StateValue or(StateValue valueArgument) {
        return new BooleanValue(value || (Boolean) valueArgument.getValue());
    }

    @Override
    public StateValue negation() {
        return new BooleanValue(!value);
    }

}
