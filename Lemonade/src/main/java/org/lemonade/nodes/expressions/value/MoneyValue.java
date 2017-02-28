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
