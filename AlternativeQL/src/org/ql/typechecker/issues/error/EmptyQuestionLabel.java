package org.ql.typechecker.issues.error;

import org.ql.ast.statement.question.QuestionLabel;
import org.ql.typechecker.issues.Issue;

public class EmptyQuestionLabel extends Issue {
    private final QuestionLabel questionLabel;

    public EmptyQuestionLabel(QuestionLabel questionLabel) {
        this.questionLabel = questionLabel;
    }

    @Override
    public String getMessage() {
        return "Question label/text cannot be empty";
    }

    @Override
    public QuestionLabel getNode() {
        return questionLabel;
    }
}
