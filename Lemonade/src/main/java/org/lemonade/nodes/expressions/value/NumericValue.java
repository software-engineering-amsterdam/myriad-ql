package org.lemonade.nodes.expressions.value;

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


    public abstract Value divide(IntegerValue that);
    public abstract Value divide(DecimalValue that);
    public abstract Value divide(MoneyValue that);
    public abstract Value divide(NumericValue that);
}
