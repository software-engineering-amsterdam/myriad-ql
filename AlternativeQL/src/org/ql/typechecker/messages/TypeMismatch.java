package org.ql.typechecker.messages;

import org.ql.ast.Node;

public class TypeMismatch implements Message {

    private final Node compare;
    private final Node against;

    public TypeMismatch(Node compare, Node against) {
        this.compare = compare;
        this.against = against;
    }

    @Override
    public Node getNode() {
        return compare;
    }

    @Override
    public String getMessage() {
        return "'" + compare + "' is not the same type as '" + against + "'";
    }
}
