package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.CustomWidgetQuestion;

public class UnsupportedWidgetForQLQuestionType extends Issue {

    private final CustomWidgetQuestion question;

    public UnsupportedWidgetForQLQuestionType(CustomWidgetQuestion question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return "Widget for QLS question " + question.getIdentifier() + " doesn't support QL question type.";
    }

    @Override
    public Node getNode() {
        return question;
    }
}
