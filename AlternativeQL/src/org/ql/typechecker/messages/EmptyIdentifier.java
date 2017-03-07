package org.ql.typechecker.messages;

import org.ql.ast.Identifier;
import org.ql.ast.Node;

public class EmptyIdentifier implements Message {

    private final Identifier id;

    public EmptyIdentifier(Identifier id) {
        this.id = id;
    }

    @Override
    public Node getNode() {
        return id;
    }

    @Override
    public String getMessage() {
        return "Identifier is empty";
    }
}
