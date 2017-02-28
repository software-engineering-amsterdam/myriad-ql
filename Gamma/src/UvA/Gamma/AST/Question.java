package UvA.Gamma.AST;

import UvA.Gamma.AST.Values.Value;
import UvA.Gamma.GUI.MainScreen;
import UvA.Gamma.Validation.IdNotFoundException;
import UvA.Gamma.Validation.IdRedeclaredException;
import UvA.Gamma.Validation.IncompatibleTypesException;
import UvA.Gamma.Validation.Validator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question implements FormItem {
    private String question;
    private String id;
    private Value value;
    private SimpleStringProperty stringValueProperty;

    public Question() {
        stringValueProperty = new SimpleStringProperty();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void idChanged(Form root, String id, String value) {
        // I don't care about that, I am an independent formitem and don't need your help
    }

    @Override
    public void accept(Validator validator) throws IdNotFoundException, IdRedeclaredException, IncompatibleTypesException {
        validator.validateRedeclaration(this);
    }

    @Override
    public boolean conformsToType(Value.Type type) {
        return value.conformsToType(type);
    }

    @Override
    public boolean isDependentOn(String id) {
        return false; // A question is never dependent on another item
    }

    @Override
    public StringProperty getStringValueProperty() {
        return this.stringValueProperty;
    }

    @Override
    public void show(MainScreen screen) {
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
