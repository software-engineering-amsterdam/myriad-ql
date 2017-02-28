package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerValue extends NumericValue<Integer> implements Comparable<IntegerValue> {

    public IntegerValue(QLType type, String value) {
        super(type, Integer.parseInt(value));
    }

    public IntegerValue(QLType type, int value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public IntegerValue plus(IntegerValue that) {
        return new IntegerValue(new QLIntegerType(), this.getValue() + that.getValue());
    }

    public DecimalValue plus(DecimalValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public MoneyValue plus(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public IntegerValue product(IntegerValue that) {
        return new IntegerValue(new QLIntegerType(), this.getValue() * that.getValue());
    }

    public DecimalValue product(DecimalValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() * that.getValue());
    }

    public MoneyValue product(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() * that.getValue());
    }

    public IntegerValue minus(IntegerValue that) {
        return new IntegerValue(new QLIntegerType(), this.getValue() - that.getValue());
    }

    public DecimalValue minus(DecimalValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() - that.getValue());
    }

    public MoneyValue minus(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() - that.getValue());
    }

    public IntegerValue divide(IntegerValue that) {
        return new IntegerValue(new QLIntegerType(), this.getValue() / that.getValue());
    }

    public DecimalValue divide(DecimalValue that) {
        return new DecimalValue(new QLDecimalType(), this.getValue() / that.getValue());
    }

    public MoneyValue divide(MoneyValue that) {
        return new MoneyValue(new QLMoneyType(), this.getValue() / that.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerValue)) {
            return false;
        }
        IntegerValue that = (IntegerValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(IntegerValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
