package semanticChecker.dependency.stateData;

import ASTnodes.expressions.literals.Identifier;
import semanticChecker.dependency.stateData.stateValues.StateValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class QuestionStateData {

    private Map<Identifier, QuestionState> symbols;

    public QuestionStateData() {
        this.symbols = new HashMap<>();
    }

    public void addState(Identifier key, QuestionState value) {
        symbols.put(key, value);
    }

    public QuestionState getState(Identifier key) {
        return symbols.get(key);
    }

    public StateValue getStateValue(Identifier key) {
        QuestionState state = symbols.get(key);
        return state.getValue();
    }
}
