package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Question extends ASTNode implements Item{

    private String question;
    private String value;
    private ValueType type;
    private Expression computedValue;

    public Question(int lineNumber) {
        super(lineNumber);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueType getType() {
        return type;
    }

    public void setType(ValueType type) {
        this.type = type;
    }

    public Expression getComputedValue() {
        return computedValue;
    }

    public void setComputedValue(Expression computedValue) {
        this.computedValue = computedValue;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
