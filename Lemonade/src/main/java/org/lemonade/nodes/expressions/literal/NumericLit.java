package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class NumericLit extends Literal {

    public NumericLit(QLType type) {
        super(type);
    }

    public NumericLit plus(NumericLit that) {
        return null;
    }

    public NumericLit minus(NumericLit that) {
        return null;
    }

    public NumericLit product(NumericLit that) {
        return null;
    }

    public NumericLit divide(NumericLit that) {
        return null;
    }

    public BooleanLit gt(NumericLit that) {
        return null;
    }

    public BooleanLit lt(NumericLit that) {
        return null;
    }

    public BooleanLit gte(NumericLit that) {
        return null;
    }

    public BooleanLit lte(NumericLit that) {
        return null;
    }

    public BooleanLit eq(NumericLit that) {
        return null;
    }

    public BooleanLit ne(NumericLit that) {
        return null;
    }
}
