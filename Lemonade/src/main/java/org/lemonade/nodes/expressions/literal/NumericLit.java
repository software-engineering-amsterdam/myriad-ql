package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class NumericLit<T> extends Literal<T> {

    public NumericLit(QLType type, T value) {
        super(type, value);
    }

<<<<<<< HEAD
    public abstract NumericLit plus(IntegerLit that);
    public abstract NumericLit plus(DecimalLit that);
    public abstract NumericLit plus(MoneyLit that);
    public abstract NumericLit plus(NumericLit that);
=======
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
>>>>>>> 559b5d66c79c5073498894bed417f4b41f62134e
}
