package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class QLDateType extends QLType {
    public QLDateType() {
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
