package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class NumericValue<T> extends Value<T> {

    public NumericValue(QLType type, T value) {
        super(type, value);
    }

    public abstract NumericValue plus(IntegerValue that);
    public abstract NumericValue plus(DecimalValue that);
    public abstract NumericValue plus(MoneyValue that);
    public abstract NumericValue plus(NumericValue that);
}
