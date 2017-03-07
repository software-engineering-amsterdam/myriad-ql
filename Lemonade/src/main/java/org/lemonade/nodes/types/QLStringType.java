package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class QLStringType extends QLType {
    public QLStringType() {
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
