package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.Question;

public class UnsupportedWidgetForQLQuestionType extends Issue {

    private final Question question;

    public UnsupportedWidgetForQLQuestionType(Question question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "Widget for QLS ComputableQuestion " + question.getIdentifier() + " doesn't support QL question type.";
    }

    @Override
    public Node getNode() {
        return question;
    }
}
