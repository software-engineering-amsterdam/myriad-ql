package org.lemonade.nodes;

import org.lemonade.visitors.ASTVisitor;

public abstract class ASTNode {

    public ASTNode() {

    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

