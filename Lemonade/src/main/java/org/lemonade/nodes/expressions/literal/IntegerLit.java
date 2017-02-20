package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerLit extends Literal {
    private int value;

    public IntegerLit(String value) {
        this.value = Integer.parseInt(value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
