package org.ql.typechecker.error;


import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class UndefinedIdentifier implements TypeError {
    private final Identifier identifier;

    public UndefinedIdentifier(Identifier identifier) {
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
