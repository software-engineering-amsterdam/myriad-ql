package org.ql.typechecker.error;

import org.ql.ast.Node;
import org.ql.ast.statement.question.QuestionText;

public class EmptyQuestionLabel implements TypeError {
    private final QuestionText questionText;

    public EmptyQuestionLabel(QuestionText questionText) {
        this.questionText = questionText;
    }

    @Override
    public String getMessage() {
        return "Question label/text cannot be empty";
    }

    @Override
    public QuestionText getNode() {
        return questionText;
    }
}
