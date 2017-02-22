package semanticChecker.dependency.stateData.stateValues;

import java.math.RoundingMode;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class IntegerValue extends StateValue {

    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public StateValue addition(StateValue valueArgument) {
        return new IntegerValue(value + (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue subtraction(StateValue valueArgument) {
        return new IntegerValue(value - (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue eq(StateValue valueArgument) {
        return new BooleanValue(value.equals((Integer) valueArgument.getValue()));
    }

    @Override
    public StateValue neq(StateValue valueArgument) {
        return new BooleanValue(value.equals((Integer) valueArgument.getValue()));
    }

    @Override
    public StateValue gt(StateValue valueArgument) {
        return new BooleanValue(value > (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue gteq(StateValue valueArgument) {
        return new BooleanValue(value >= (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue lt(StateValue valueArgument) {
        return new BooleanValue(value < (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue lteq(StateValue valueArgument) {
        return new BooleanValue(value <= (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue division(StateValue valueArgument) {
        return new IntegerValue(value / (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue multiplication(StateValue valueArgument) {
        return new IntegerValue(value * (Integer) valueArgument.getValue());
    }

    @Override
    public StateValue negative(StateValue valueArgument) {
        return new IntegerValue(-((Integer) valueArgument.getValue()));
    }

    @Override
    public StateValue positive(StateValue valueArgument) {
        return new IntegerValue(Math.abs(((Integer) valueArgument.getValue())));
    }
}
