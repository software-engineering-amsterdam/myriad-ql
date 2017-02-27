package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerLit extends NumericLit {
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

//    public IntegerLit plus(IntegerLit that) {
//        return new IntegerLit(new QLIntegerType(), this.value + that.value);
//    }

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
