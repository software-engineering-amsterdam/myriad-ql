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

    public abstract NumericLit plus(IntegerLit that);
    public abstract NumericLit plus(DecimalLit that);
    public abstract NumericLit plus(MoneyLit that);
    public abstract NumericLit plus(NumericLit that);
}
