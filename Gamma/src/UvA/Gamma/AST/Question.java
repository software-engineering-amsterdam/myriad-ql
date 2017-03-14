package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.FXMLExampleController;
import UvA.Gamma.Validation.*;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question implements FormItem {
    private String question;
    private String id;
    private Value value;

    public Question(String question, String id, Value value) {
        this.question = question;
        this.id = id;
        this.value = value;
    }

    public String getQuestion() {
        assert question != null;
        return question;
    }

    public boolean check(TypeChecker checker, String newValue) {
        assert value != null;
        return checker.check(value, newValue);
    }


    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException {
        validator.validateRedeclaration(this);
    }

    @Override
    public String validateRedeclaration(FormItem item) {
        return item != this && item.hasId(this.id) ? this.id : null;
    }

    @Override
    public Value.Type validateIdentifierType(String identifier, Value.Type type) {
        return this.id.equals(identifier) && !value.conformsToType(type) ? value.getType() : null;
    }

    @Override
    public Pair<String> validateCyclicDependency(FormItem item) {
        return new Pair<>(item.isDependentOn(this.id) ? this.id : null, item.isDependencyOf(this));
    }

    @Override
    public boolean isDependentOn(String id) {
        return false; // A question is never dependent on another item
    }

    @Override
    public String isDependencyOf(FormItem item) {
        return item.isDependentOn(this.id) ? this.id : null;
    }

    @Override
    public void show(FXMLExampleController screen) {
        value.showQuestion(screen, this);
    }

    @Override
    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    @Override
    public boolean containsId(String id) {
        return hasId(id);
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        // I don't care about that, I am an independent formitem and don't need your help
    }

    @Override
    public String toString() {
        return "<Question>: " + question + " " + id + ": " + value.getType();
    }
}
