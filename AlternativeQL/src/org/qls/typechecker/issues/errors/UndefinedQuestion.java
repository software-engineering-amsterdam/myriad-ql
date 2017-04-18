package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.WidgetQuestion;

public class UndefinedQuestion extends Issue {

    private final WidgetQuestion question;

    public UndefinedQuestion(WidgetQuestion question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "QLS question: " + question.getId() + " is not defined in QL source file.";
    }

    @Override
    public Node getNode() {
        return question;
    }
}
