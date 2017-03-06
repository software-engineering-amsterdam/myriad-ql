package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class DecimalValue extends NumericValue<Double> implements Comparable<DecimalValue> {

    public DecimalValue(String value) {
        super(new QLDecimalType(), Double.parseDouble(value));
    }

    public DecimalValue(double value) {
        super(new QLDecimalType(), value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public DecimalValue plus(IntegerValue that) {
        return new DecimalValue(this.getValue() + that.getValue());
    }

    public DecimalValue plus(DecimalValue that) {
        return new DecimalValue(this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(this.getValue() + that.getValue());
    }

    public DecimalValue minus(IntegerValue that) {
        return new DecimalValue(this.getValue() - that.getValue());
    }

    public DecimalValue minus(final DecimalValue that) {
        return new DecimalValue(this.getValue() - that.getValue());
    }

    public MoneyValue minus(final MoneyValue that) {
        return new MoneyValue(this.getValue() - that.getValue());
    }

    public DecimalValue product(final IntegerValue that) {
        return new DecimalValue(this.getValue() * that.getValue());
    }

    public DecimalValue product(final DecimalValue that) {
        return new DecimalValue(this.getValue() * that.getValue());
    }

    public MoneyValue product(final MoneyValue that) {
        return new MoneyValue(this.getValue() * that.getValue());
    }

    public DecimalValue divide(final IntegerValue that) {
        return new DecimalValue((int) (this.getValue() / that.getValue()));
    }

    public DecimalValue divide(final DecimalValue that) {
        return new DecimalValue(this.getValue() / that.getValue());
    }

    public MoneyValue divide(final MoneyValue that) {
        return new MoneyValue(this.getValue() / that.getValue());
    }

    @Override
    public BooleanValue gT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DecimalValue) that) == 1);
    }

    @Override
    public BooleanValue gTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DecimalValue) that) >= 0);
    }

    @Override
    public BooleanValue lT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DecimalValue) that) == -1);
    }

    @Override
    public BooleanValue lTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DecimalValue) that) <= 0);
    }

    @Override
    public DecimalValue neg() {
        return new DecimalValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DecimalValue)) {
            return false;
        }
        DecimalValue that = (DecimalValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(DecimalValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
