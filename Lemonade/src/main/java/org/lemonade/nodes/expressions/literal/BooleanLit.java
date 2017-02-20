package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BooleanLit extends Literal {
    private boolean value;

    public BooleanLit(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
