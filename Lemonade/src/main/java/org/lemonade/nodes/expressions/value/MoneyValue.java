package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class MoneyValue extends NumericValue<Double> implements Comparable<MoneyValue> {

    public MoneyValue(QLType type, String value) {
        super(type, Double.parseDouble(value));
        assert type instanceof QLMoneyType;
    }

    public MoneyValue(QLMoneyType type, double value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public MoneyValue plus(IntegerValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public MoneyValue plus(DecimalValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public NumericValue<?> plus(NumericValue<?> that) {
        return that.plus(this);
    }

    public MoneyValue minus(final IntegerValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() - that.getValue());
    }

    public MoneyValue minus(final DecimalValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() - that.getValue());
    }

    public MoneyValue minus(final MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() - that.getValue());
    }

    public MoneyValue product(final IntegerValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() * that.getValue());
    }

    public MoneyValue product(final DecimalValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() * that.getValue());
    }

    public MoneyValue product(final MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() * that.getValue());
    }

    @Override
    public NumericValue<?> divide(IntegerValue that) {
        return null;
    }

    @Override
    public NumericValue<?> divide(DecimalValue that) {
        return null;
    }

    @Override
    public NumericValue<?> divide(MoneyValue that) {
        return null;
    }

    @Override
    public NumericValue<?> divide(NumericValue<?> that) {
        return null;
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MoneyValue)){
            return false;
        }
        MoneyValue that = (MoneyValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(MoneyValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        }
        else if (this.getValue() > that.getValue()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
