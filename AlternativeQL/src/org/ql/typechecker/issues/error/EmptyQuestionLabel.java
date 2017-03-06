package org.ql.typechecker.issues.error;

import org.ql.ast.statement.question.QuestionText;
import org.ql.typechecker.issues.Issue;

public class EmptyQuestionLabel implements Issue {
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
