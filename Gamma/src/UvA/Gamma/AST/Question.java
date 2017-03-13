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

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public void idChanged(Form root, FormItem changed, String value) {
        // I don't care about that, I am an independent formitem and don't need your help
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
    public boolean validateIdentifierType(String identifier, Value.Type type) {
        return this.id.equals(identifier) && !value.conformsToType(type);
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
        screen.showQuestion(this);
    }

    @Override
    public boolean hasId(String id) {
        return this.id.equals(id);
    }

    @Override
    public String toString() {
        return "<Question>: " + question + " " + id + ": " + value.getType();
    }

    @Override
    public Value.Type getType() {
        return value.getType();
    }
}
