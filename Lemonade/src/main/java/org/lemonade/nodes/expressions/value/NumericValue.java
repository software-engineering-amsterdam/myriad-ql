package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class NumericValue<T> extends Value<T> {

    public NumericValue(QLType type, T value) {
        super(type, value);
    }

    public abstract NumericValue<?> plus(IntegerValue that);
    public abstract NumericValue<?> plus(DecimalValue that);
    public abstract NumericValue<?> plus(MoneyValue that);
    public abstract NumericValue<?> plus(NumericValue<?> that);

    public abstract NumericValue<?> product(IntegerValue that);
    public abstract NumericValue<?> product(DecimalValue that);
    public abstract NumericValue<?> product(MoneyValue that);

    public NumericValue<?> product(final NumericValue<?> that) {
        if (that.getType().isOf(QLIntegerType.class)) {
            return this.product((IntegerValue) that);
        } else if (that.getType().isOf(QLDecimalType.class)) {
            return this.product((DecimalValue) that);
        } else if (that.getType().isOf(QLMoneyType.class)) {
            return this.product((MoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public abstract NumericValue<?> minus(IntegerValue that);
    public abstract NumericValue<?> minus(DecimalValue that);
    public abstract NumericValue<?> minus(MoneyValue that);

    public NumericValue<?> minus(final NumericValue<?> that) {
        if (that.getType().isOf(QLIntegerType.class)) {
            return this.minus((IntegerValue) that);
        } else if (that.getType().isOf(QLDecimalType.class)) {
            return this.minus((DecimalValue) that);
        } else if (that.getType().isOf(QLMoneyType.class)) {
            return this.minus((MoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public abstract Value<?> divide(IntegerValue that);
    public abstract Value<?> divide(DecimalValue that);
    public abstract Value<?> divide(MoneyValue that);
    public abstract Value<?> divide(NumericValue<?> that);
}
