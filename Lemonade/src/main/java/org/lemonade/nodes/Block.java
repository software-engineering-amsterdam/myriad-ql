package org.lemonade.nodes;

import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public abstract class Block extends ASTNode {
    public Block() {
        super();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
