package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.Validation.TypeChecker;

/**
 * Created by Tjarco, 28-02-17.
 */
public class StringValue extends Value {

    private String value;

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public String computableString() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean conformsToType(Type type) {
        return false; // No operations on Strings
    }

    @Override
    public boolean validate(String value, TypeChecker typeChecker) {
        return true; // String is always of a valid QL type String.
    }

    @Override
    public void showQuestion(FXMLExampleController screen, Question question) {
        screen.showQuestion(question);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
