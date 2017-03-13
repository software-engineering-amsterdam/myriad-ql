package org.lemonade.nodes.types;

import org.lemonade.visitors.interfaces.TypeVisitor;

/**
 *
 */
public class QLMoneyType extends QLNumberType {

    public QLMoneyType() {
        super(3);
    }

    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "money";
    }
}
