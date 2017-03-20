package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.CustomWidgetQuestion;

public class DuplicateQLSQuestion extends Issue {

    private final CustomWidgetQuestion question;

    public DuplicateQLSQuestion(CustomWidgetQuestion question) {
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
