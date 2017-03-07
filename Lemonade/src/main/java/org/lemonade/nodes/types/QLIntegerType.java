package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class QLIntegerType extends QLNumberType {

    public QLIntegerType() {
        super(1);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "integer";
    }
}
