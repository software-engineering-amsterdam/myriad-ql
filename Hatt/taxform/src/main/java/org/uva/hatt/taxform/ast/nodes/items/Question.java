package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.types.ValueType;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Question extends Item{

    private final String question;
    private final String value;
    private final ValueType type;

    public Question(int lineNumber, String question, String value, ValueType type) {
        super(lineNumber);
        this.question = question;
        this.value = value;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String getValue() {
        return value;
    }

    public ValueType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
