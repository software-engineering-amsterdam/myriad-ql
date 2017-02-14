package UvA.Gamma.AST;

import UvA.Gamma.GUI.MainScreen;

/**
 * Created by Tjarco, 14-02-17.
 */
public class Question implements FormItem {
    private String question;
    private String id;
    private String type;

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
    public void show(MainScreen screen) {
        screen.showQuestion(this);
    }

    @Override
    public String toString() {
        return "<Question>: " + question + " " + id + ": " + type;
    }
}
