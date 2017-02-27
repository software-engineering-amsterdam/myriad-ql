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

    T plus(MoneyLit that) {
        return new NumericLit(this.value + that.value);
    }
}
