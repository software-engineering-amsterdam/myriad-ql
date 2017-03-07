package org.ql.ast.statement.question;

import org.ql.ast.AbstractNode;

public class QuestionText extends AbstractNode {
    private final String questionText;

    public QuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String toString() {
        return questionText;
    }
}
