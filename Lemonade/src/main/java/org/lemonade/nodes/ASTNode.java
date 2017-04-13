package org.lemonade.nodes;

public abstract class ASTNode {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

