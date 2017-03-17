package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.typechecker.issues.Issue;
import org.qls.ast.page.Question;

public class UndefinedQLQuestion extends Issue {

    private Question question;

    public UndefinedQLQuestion(Question question) {
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
