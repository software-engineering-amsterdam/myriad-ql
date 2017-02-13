package UvA.Gamma.Models;

import UvA.Gamma.Models.QLValues.QLInt;
import UvA.Gamma.Models.QLValues.QLValue;

public class QLInput {
    private String identifier;
    private String question;
    private QLValue value;

    public QLInput(String identifier, String question, String type) {
        this.identifier = identifier;
        this.question = question;
        QLValue.Type t = QLValue.Type.valueOf(type.toUpperCase());
        switch (t){
            case INTEGER:
                value = new QLInt();
                break;
        }
    }


    public QLValue getValue() {
        return value;
    }

    public QLValue.Type getType(){
        return value.getType();
    }


    //TODO: Better exception
    public void setValue(Object value) throws ClassCastException{
        switch (this.value.getType()){
            case INTEGER:
                ((QLInt)value).setValue((Integer)value);
                break;
        }
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
