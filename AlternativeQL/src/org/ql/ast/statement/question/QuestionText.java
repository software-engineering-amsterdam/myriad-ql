package org.ql.ast.statement.question;

import org.ql.ast.AbstractNode;
import org.ql.ast.Visitor;

public class QuestionText extends AbstractNode {
    private final String questionText;

    public QuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String toString() {
        return questionText;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
