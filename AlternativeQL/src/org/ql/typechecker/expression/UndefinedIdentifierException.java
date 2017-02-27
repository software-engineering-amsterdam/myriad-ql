package org.ql.typechecker.expression;


import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class UndefinedIdentifierException extends RuntimeException implements TypeError {
    private final Identifier identifier;

    public UndefinedIdentifierException(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Node getNode() {
        return identifier;
    }

    @Override
    public String getMessage() {
        return "Undefined identifier '" + identifier + "' used";
    }
}
