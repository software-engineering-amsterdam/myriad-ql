package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerLit extends NumericLit<Integer> implements Comparable<IntegerLit>{

    public IntegerLit(QLType type, String value) {
        super(type, Integer.parseInt(value));
    }

    public IntegerLit(QLType type, int value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public IntegerLit plus(IntegerLit that) {
        return new IntegerLit(new QLIntegerType(), this.getValue() + that.getValue());
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
        return Integer.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerLit)){
            return false;
        }
        IntegerLit that = (IntegerLit) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(IntegerLit that) {
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
