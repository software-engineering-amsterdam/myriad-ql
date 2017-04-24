package org.ql.typechecker.issues.error;

import org.ql.ast.identifier.Identifier;
import org.ql.typechecker.issues.Issue;

public class EmptyIdentifier extends Issue {
    private final Identifier identifier;

    public EmptyIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getMessage() {
        return "Identifier cannot be empty";
    }

    @Override
    public Identifier getNode() {
        return identifier;
    }
}
