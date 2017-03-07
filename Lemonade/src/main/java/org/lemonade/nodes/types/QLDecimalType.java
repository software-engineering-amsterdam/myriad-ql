package org.lemonade.nodes.types;

import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class QLDecimalType extends QLNumberType {

    public QLDecimalType() {
        super(2);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "decimal";
    }

}
