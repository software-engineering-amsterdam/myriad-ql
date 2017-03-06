package org.lemonade.nodes;

/**
 *
 */
public abstract class Body extends ASTNode{
    public Body() {

    }

    public abstract boolean isQuestion();
    public abstract boolean isConditional();
}
