package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class MoneyValue extends NumericValue<Double> implements Comparable<MoneyValue> {

    public MoneyValue(String value) {
        super(new QLMoneyType(), Double.parseDouble(value));
    }

    public MoneyValue(double value) {
        super(new QLMoneyType(), value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public MoneyValue plus(IntegerValue that) {
        return new MoneyValue(this.getValue() + that.getValue());
    }

    public MoneyValue plus(DecimalValue that) {
        return new MoneyValue(this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(this.getValue() + that.getValue());
    }

    public MoneyValue minus(final IntegerValue that) {
        return new MoneyValue(this.getValue() - that.getValue());
    }

    public MoneyValue minus(final DecimalValue that) {
        return new MoneyValue(this.getValue() - that.getValue());
    }

    public MoneyValue minus(final MoneyValue that) {
        return new MoneyValue(this.getValue() - that.getValue());
    }

    public MoneyValue product(final IntegerValue that) {
        return new MoneyValue(this.getValue() * that.getValue());
    }

    public MoneyValue product(final DecimalValue that) {
        return new MoneyValue(this.getValue() * that.getValue());
    }

    public MoneyValue product(final MoneyValue that) {
        return new MoneyValue(this.getValue() * that.getValue());
    }

    public MoneyValue divide(final IntegerValue that) {
        return new MoneyValue((int) (this.getValue() / that.getValue()));
    }

    public MoneyValue divide(final DecimalValue that) {
        return new MoneyValue(this.getValue() / that.getValue());
    }

    public MoneyValue divide(final MoneyValue that) {
        return new MoneyValue(this.getValue() / that.getValue());
    }

    @Override
    public BooleanValue gT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((MoneyValue) that) == 1);
    }

    @Override
    public BooleanValue gTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((MoneyValue) that) >= 0);
    }

    @Override
    public BooleanValue lT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((MoneyValue) that) == -1);
    }

    @Override
    public BooleanValue lTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((MoneyValue) that) <= 0);
    }

    @Override
    public MoneyValue neg() {
        return new MoneyValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MoneyValue)) {
            return false;
        }
        MoneyValue that = (MoneyValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(MoneyValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
