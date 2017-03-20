package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.CustomWidgetQuestion;
import org.qls.ast.page.WidgetQuestion;

public class UndefinedQLQuestion extends Issue {

    private final WidgetQuestion question;

    public UndefinedQLQuestion(WidgetQuestion question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "QLS question: " + question.getIdentifier() + " is not defined in QL source file.";
    }

    @Override
    public Node getNode() {
        return question;
    }
}
