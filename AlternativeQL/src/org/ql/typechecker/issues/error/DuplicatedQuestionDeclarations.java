package org.ql.typechecker.issues.error;

import org.ql.ast.statement.Question;
import org.ql.typechecker.issues.Issue;

public class DuplicatedQuestionDeclarations extends Issue {
    private final Question question;

    public DuplicatedQuestionDeclarations(Question question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "Question '" + question.getId() + "' has duplicate(s)";
    }

    @Override
    public Question getNode() {
        return question;
    }
}
