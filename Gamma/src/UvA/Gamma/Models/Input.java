package UvA.Gamma.Models;

public class Input {
    private String identifier;
    private String question;
    private QLValue value;

    public Input(String identifier, String question, QLValue value) {
        this.identifier = identifier;
        this.question = question;
        this.value = value;
    }


    public QLValue getValue() {
        return value;
    }

    public QLType getType(){
        return value.getType();
    }

    public void setValue(Object value) {
        this.value.setValue(value);
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "<input>: " + identifier + ": " + question + " " + value;
    }
}
