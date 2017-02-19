package org.lemonade.nodes;

import org.lemonade.visitors.BlockVisitor;

/**
 *
 */
public abstract class Block extends ASTNode {
    public Block() {
        super();
    }

    public <T> T accept(BlockVisitor<T> visitor){
        return visitor.visit(this);
    }
}
