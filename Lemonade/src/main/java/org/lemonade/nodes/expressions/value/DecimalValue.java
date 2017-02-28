package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.types.*;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class DecimalValue extends NumericValue<Double> implements Comparable<DecimalValue> {

    public DecimalValue(QLType type, String value) {
        super(type, Double.parseDouble(value));
        assert type instanceof QLDecimalType;
    }

    public DecimalValue(QLDecimalType type, double value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public DecimalValue plus(IntegerValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public DecimalValue plus(DecimalValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public NumericValue plus(NumericValue that) {
        return that.plus(this);
    }

    @Override
    public DecimalValue divide(IntegerValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() / that.getValue());
    }

    @Override
    public NumericValue divide(DecimalValue that) {
        return null;
    }

    @Override
    public NumericValue divide(MoneyValue that) {
        return null;
    }

    @Override
    public NumericValue divide(NumericValue that) {
        return null;
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DecimalValue)){
            return false;
        }
        DecimalValue that = (DecimalValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(DecimalValue that) {
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
