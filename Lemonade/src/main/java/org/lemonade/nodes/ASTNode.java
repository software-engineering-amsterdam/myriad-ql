package org.lemonade.nodes;

import org.lemonade.visitors.ASTVisitor;

public abstract class ASTNode {
    private Position position;

    public ASTNode() {

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

