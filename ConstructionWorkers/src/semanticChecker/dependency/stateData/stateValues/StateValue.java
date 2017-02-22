package semanticChecker.dependency.stateData.stateValues;


/**
 * Created by LGGX on 22-Feb-17.
 */
public abstract class StateValue {

    public abstract Object getValue();

    public StateValue addition(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue subtraction(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue and(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue or(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue negation() {
        return new UndefinedValue();
    }

    public StateValue eq(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue neq(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue gt(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue gteq(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue lt(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue lteq(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue division(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue multiplication(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue negative(StateValue value) {
        return new UndefinedValue();
    }

    public StateValue positive(StateValue value) {
        return new UndefinedValue();
    }


}
