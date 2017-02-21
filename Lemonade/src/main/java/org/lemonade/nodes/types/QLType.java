package org.lemonade.nodes.types;

import org.lemonade.nodes.ASTNode;
import org.lemonade.visitors.ASTVisitor;

/**
 * A wrapper for QLTypemm, couldn't use it in the Visitor otherwise..
 */
public abstract class QLType implements ASTNode {

    public boolean isOf(QLType other) {
        return this.getClass() == other.getClass();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
