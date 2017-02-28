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

    public NumericValue<?> plus(NumericValue<?> that) {
        return that.plus(this);
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

    public Value<?> divide(IntegerValue that) {
        if (this.getValue() == 0 || that.getValue() == 0) {
            return new UndefinedValue(new QLIntegerType(), "Division with zero");
        }
        return new IntegerValue(new QLIntegerType(), this.getValue() / that.getValue());
    }

    @Override
    public Value<?> divide(DecimalValue that) {
        if (this.getValue() == 0 || that.getValue() == 0) {
            return new UndefinedValue(new QLDecimalType(), "Division with zero");
        }
        return new DecimalValue(new QLDecimalType(), this.getValue() / that.getValue());
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
