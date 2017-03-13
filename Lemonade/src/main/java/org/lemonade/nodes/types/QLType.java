package org.lemonade.nodes.types;

import org.lemonade.nodes.ASTNode;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

/**
 *
 */
public abstract class QLType extends ASTNode {

    public boolean isOf(Class<?> other) {
        return this.getClass().equals(other);
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

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
