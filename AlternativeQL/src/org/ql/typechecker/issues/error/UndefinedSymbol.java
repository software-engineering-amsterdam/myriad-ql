package org.ql.typechecker.issues.error;


import org.ql.ast.identifier.Identifier;
import org.ql.typechecker.issues.Issue;

public class UndefinedSymbol extends Issue {
    private final Identifier identifier;

    public UndefinedSymbol(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Identifier getNode() {
        return identifier;
    }

    @Override
    public String getMessage() {
        return "Undefined identifier '" + identifier + "' used";
    }
}
