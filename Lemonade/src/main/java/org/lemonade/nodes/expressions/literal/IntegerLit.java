package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerLit extends Literal {
    private int value;

    public IntegerLit(QLType type, String value) {
        super(type);
        this.value = Integer.parseInt(value);
    }

    public IntegerLit(QLType type, int value) {
        super(type);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public IntegerLit plus(IntegerLit that) {
        return new IntegerLit(new QLIntegerType(), this.value + that.value);
    }
}
