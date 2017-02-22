package semanticChecker.dependency.stateData.stateValues;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class MoneyValue extends StateValue {

    private final BigDecimal value;

    public MoneyValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public StateValue addition(StateValue valueArgument) {
        return new MoneyValue(value.add((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue subtraction(StateValue valueArgument) {
        return new MoneyValue(value.subtract((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue eq(StateValue valueArgument) {
        return new BooleanValue(value.equals((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue neq(StateValue valueArgument) {
        return new BooleanValue(!value.equals((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue gt(StateValue valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) > 0);
    }

    @Override
    public StateValue gteq(StateValue valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) >= 0);
    }

    @Override
    public StateValue lt(StateValue valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) < 0);
    }

    @Override
    public StateValue lteq(StateValue valueArgument) {
        return new BooleanValue(value.compareTo((BigDecimal) valueArgument.getValue()) <= 0);
    }

    @Override
    public StateValue division(StateValue valueArgument) {
        return new MoneyValue(value.divide((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue multiplication(StateValue valueArgument) {
        return new MoneyValue(value.multiply((BigDecimal) valueArgument.getValue()));
    }

    @Override
    public StateValue negative(StateValue valueArgument) {
        return new MoneyValue(((BigDecimal) valueArgument.getValue()).negate());
    }

    @Override
    public StateValue positive(StateValue valueArgument) {
        return new MoneyValue(((BigDecimal) valueArgument.getValue()).abs());
    }
}