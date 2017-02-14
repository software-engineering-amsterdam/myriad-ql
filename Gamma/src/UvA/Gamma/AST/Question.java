package UvA.Gamma.AST;

import UvA.Gamma.GUI.MainScreen;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question implements FormItem {
    private String question;
    private String id;
    private String type;
    private SimpleStringProperty stringValueProperty;

    public Question(){
        stringValueProperty = new SimpleStringProperty();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
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
    public boolean hasID(String id) {
        return this.id.equals(id);
    }

    @Override
    public String toString() {
        return "<Question>: " + question + " " + id + ": " + type;
    }
}
