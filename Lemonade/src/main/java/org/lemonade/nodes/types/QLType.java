package org.lemonade.nodes.types;

import org.lemonade.nodes.ASTNode;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public abstract class QLType implements ASTNode {

    public boolean isOf(Class other) {
        return this.getClass().equals(other);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isComparable() {
        return false;
    }
}
