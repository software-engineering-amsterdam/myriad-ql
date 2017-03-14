package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;

/**
 *
 */

public abstract class NumericLiteral<T> extends ComparableLiteral<T> {

    public NumericLiteral(QLType type, T value) {
        super(type, value);
    }

    public abstract NumericLiteral<?> plus(IntegerLiteral that);

    public abstract NumericLiteral<?> plus(DecimalLiteral that);

    public abstract NumericLiteral<?> plus(MoneyLiteral that);

    public abstract NumericLiteral<?> minus(IntegerLiteral that);

    public abstract NumericLiteral<?> minus(DecimalLiteral that);

    public abstract NumericLiteral<?> minus(MoneyLiteral that);

    public abstract NumericLiteral<?> product(IntegerLiteral that);

    public abstract NumericLiteral<?> product(DecimalLiteral that);

    public abstract NumericLiteral<?> product(MoneyLiteral that);

    public abstract NumericLiteral<?> divide(IntegerLiteral that);

    public abstract NumericLiteral<?> divide(DecimalLiteral that);

    public abstract NumericLiteral<?> divide(MoneyLiteral that);

    public abstract NumericLiteral<?> neg();

    public NumericLiteral<?> plus(final NumericLiteral<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.plus((IntegerLiteral) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.plus((DecimalLiteral) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.plus((MoneyLiteral) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NumericLiteral<?> product(final NumericLiteral<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.product((IntegerLiteral) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.product((DecimalLiteral) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.product((MoneyLiteral) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NumericLiteral<?> minus(final NumericLiteral<?> that) {
        if (that.getType().isOf(QLIntegerType.class)) {
            return this.minus((IntegerLiteral) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.minus((DecimalLiteral) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.minus((MoneyLiteral) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public NumericLiteral<?> divide(final NumericLiteral<?> that) {
        if (that.getType().isOf(new QLIntegerType().getClass())) {
            return this.divide((IntegerLiteral) that);
        } else if (that.getType().isOf(new QLDecimalType().getClass())) {
            return this.divide((DecimalLiteral) that);
        } else if (that.getType().isOf(new QLMoneyType().getClass())) {
            return this.divide((MoneyLiteral) that);

        } else {
            throw new IllegalArgumentException();
        }
    }
}
