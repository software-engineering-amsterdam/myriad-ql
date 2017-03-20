package org.ql.ast.statement.question;

import org.ql.ast.Node;

public class QuestionLabel extends Node {
    private final String questionText;

    public QuestionLabel(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public int hashCode() {
        return questionText.hashCode();
    }

    public boolean isEmpty() {
        return questionText.isEmpty();
    }

    public String toString() {
        return questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionLabel that = (QuestionLabel) o;

        return questionText != null ? questionText.equals(that.questionText) : that.questionText == null;

    }

    public <T, C> T accept(QuestionLabelVisitor<T, C> visitor, C context) {
        return visitor.visitQuestionLabel(this, context);
    }
}
