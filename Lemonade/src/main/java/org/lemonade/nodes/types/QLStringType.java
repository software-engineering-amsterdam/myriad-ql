package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

/**
 *
 */
public class QLStringType extends QLType {
    public QLStringType() {
    }

    public <T> T accept(TypeVisitor<T> visitor) {
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
