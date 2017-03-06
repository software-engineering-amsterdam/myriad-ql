package org.lemonade.nodes;

/**
 *
 */
public abstract class Body implements ASTNode {
    public Body() {

    }

    public abstract boolean isQuestion();
    public abstract boolean isConditional();
}
