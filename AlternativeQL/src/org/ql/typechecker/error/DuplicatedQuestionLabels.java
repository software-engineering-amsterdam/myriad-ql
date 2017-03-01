package org.ql.typechecker.error;

import org.ql.ast.Node;
import org.ql.ast.statement.Question;

public class DuplicatedQuestionLabels implements TypeError {
    private final Question question;

    public DuplicatedQuestionLabels(Question question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "Question '" + question.getId() + "' label has duplicate(s)";
    }

    @Override
    public Question getNode() {
        return question;
    }
}
