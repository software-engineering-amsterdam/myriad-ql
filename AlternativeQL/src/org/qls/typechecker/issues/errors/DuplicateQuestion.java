package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.WidgetQuestion;

public class DuplicateQuestion extends Issue {

    private final WidgetQuestion question;

    public DuplicateQuestion(WidgetQuestion question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "QLS question " + question.getIdentifier() + " is already defined in the QLS source.";
    }

    @Override
    public Node getNode() {
        return question;
    }
}
