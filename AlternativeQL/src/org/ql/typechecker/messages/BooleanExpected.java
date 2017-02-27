package org.ql.typechecker.messages;

import org.ql.ast.Node;

public class BooleanExpected implements Message {
    private final Node affectedNode;

    public BooleanExpected(Node affectedNode) {
        this.affectedNode = affectedNode;
    }

    @Override
    public Node getNode() {
        return affectedNode;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
