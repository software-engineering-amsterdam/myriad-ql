package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.*;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class DecimalLit extends NumericLit<Double> implements Comparable<DecimalLit> {

    public DecimalLit(QLType type, String value) {
        super(type, Double.parseDouble(value));
        assert type instanceof QLDecimalType;
    }

    public DecimalLit(QLDecimalType type, double value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public DecimalLit plus(IntegerLit that) {
        return new DecimalLit(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public DecimalLit plus(DecimalLit that) {
        return new DecimalLit(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public MoneyLit plus(MoneyLit that) {
        return new MoneyLit(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public NumericLit plus(NumericLit that) {
        return that.plus(this);
    }
    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DecimalLit)){
            return false;
        }
        DecimalLit that = (DecimalLit) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(DecimalLit that) {
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
