package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class DecimalLit extends Literal {
    private double value;

    public DecimalLit(QLType type, String value) {
        super(type);
        assert type instanceof QLDecimalType;
        this.value = Double.parseDouble(value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
