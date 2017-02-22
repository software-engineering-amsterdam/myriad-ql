package semanticChecker.dependency.stateData;

import semanticChecker.dependency.stateData.stateValues.StateValue;
import semanticChecker.dependency.stateData.stateValues.UndefinedValue;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class QuestionState {

    private StateValue value;

    public QuestionState() {
        this.value = new UndefinedValue();
    }

    public QuestionState(StateValue value) {
        this.value = value;
    }

    public StateValue getValue() {
        return value;
    }

    public void setValue(StateValue value) {
        this.value = value;
    }
}
