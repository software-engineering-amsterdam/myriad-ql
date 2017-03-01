package org.ql.typechecker.error;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class EmptyIdentifier implements TypeError {
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
