package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class IntegerLiteral extends NumericLiteral<Integer> implements Comparable<IntegerLiteral> {

    public IntegerLiteral(String value) {
        super(new QLIntegerType(), Integer.parseInt(value));
    }

    public IntegerLiteral(int value) {
        super(new QLIntegerType(), value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public IntegerLiteral plus(IntegerLiteral that) {
        return new IntegerLiteral(this.getValue() + that.getValue());
    }

    public DecimalLiteral plus(DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() + that.getValue());
    }

    public MoneyLiteral plus(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() + that.getValue());
    }

    public IntegerLiteral product(IntegerLiteral that) {
        return new IntegerLiteral(this.getValue() * that.getValue());
    }

    public DecimalLiteral product(DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() * that.getValue());
    }

    public MoneyLiteral product(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() * that.getValue());
    }

    public IntegerLiteral minus(IntegerLiteral that) {
        return new IntegerLiteral(this.getValue() - that.getValue());
    }

    public DecimalLiteral minus(DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() - that.getValue());
    }

    public MoneyLiteral minus(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() - that.getValue());
    }

    public IntegerLiteral divide(IntegerLiteral that) {
        return new IntegerLiteral(this.getValue() / that.getValue());
    }

    public DecimalLiteral divide(DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() / that.getValue());
    }

    public MoneyLiteral divide(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() / that.getValue());
    }

    @Override
    public BooleanLiteral gT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((IntegerLiteral) that) == 1);
    }

    @Override
    public BooleanLiteral gTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((IntegerLiteral) that) >= 0);
    }

    @Override
    public BooleanLiteral lT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((IntegerLiteral) that) == -1);
    }

    @Override
    public BooleanLiteral lTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((IntegerLiteral) that) <= 0);
    }

    @Override
    public IntegerLiteral neg() {
        return new IntegerLiteral(-this.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegerLiteral)) {
            return false;
        }
        IntegerLiteral that = (IntegerLiteral) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(IntegerLiteral that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
