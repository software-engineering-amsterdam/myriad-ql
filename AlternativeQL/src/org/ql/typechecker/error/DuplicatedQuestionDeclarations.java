package org.ql.typechecker.error;

import org.ql.ast.statement.Question;

public class DuplicatedQuestionDeclarations implements TypeError {
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
