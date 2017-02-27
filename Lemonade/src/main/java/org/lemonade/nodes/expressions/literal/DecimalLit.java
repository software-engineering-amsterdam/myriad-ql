package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class DecimalLit extends NumericLit {
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

    @Override
    public NumericLit plus(NumericLit that) {
        return null;
    }

    @Override
    public NumericLit minus(NumericLit that) {
        return null;
    }

    @Override
    public NumericLit product(NumericLit that) {
        return null;
    }

    @Override
    public NumericLit divide(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit gt(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit lt(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit gte(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit lte(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit eq(NumericLit that) {
        return null;
    }

    @Override
    public BooleanLit ne(NumericLit that) {
        return null;
    }
}
