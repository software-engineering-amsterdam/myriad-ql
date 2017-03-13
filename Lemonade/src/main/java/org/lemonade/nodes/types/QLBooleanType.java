package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

/**
 *
 */
public class QLBooleanType extends QLType {

    public QLBooleanType() {
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean isComparable() {
        return true;
    }
}
