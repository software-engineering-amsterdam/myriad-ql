package org.ql.typechecker.issues.warning;

import org.ql.ast.statement.Question;
import org.ql.typechecker.issues.Issue;

public class DuplicatedQuestionLabels extends Issue {
    private final Question question;

    public DuplicatedQuestionLabels(Question question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "CustomWidgetQuestion '" + question.getId() + "' label has duplicate(s)";
    }

    @Override
    public Question getNode() {
        return question;
    }
}
