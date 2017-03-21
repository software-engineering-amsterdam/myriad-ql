package org.qls.typechecker.issues.errors;

import org.ql.ast.Node;
import org.ql.ast.identifier.Identifier;
import org.ql.typechecker.issues.Issue;

public class UnusedQuestion extends Issue {
    private final Identifier node;

    public UnusedQuestion(Identifier identifier) {
        node = identifier;
    }

    @Override
    public String getMessage() {
        return "Unused question " + node;
    }

    @Override
    public Node getNode() {
        return node;
    }
}
