package org.ql.ast;

public class Question {
    private final Identifier id;
    private String question;
    private Type type;

    public Question(Identifier id, String question, Type type) {
        this.id = id;
        this.question = question;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
