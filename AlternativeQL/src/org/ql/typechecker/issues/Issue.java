package org.ql.typechecker.issues;

import org.ql.ast.Node;
import org.ql.ast.SourceLocation;

public abstract class Issue {
    public abstract String getMessage();
    public abstract Node getNode();

    public String getFullMessage() {
        return getMessage() + " " + onLine();
    }

    private String onLine() {
        SourceLocation sourceLocation = getNode().getSourceLocation();
        return "on line " + sourceLocation.getLine() + ":" + sourceLocation.getColumn();
    }
}
