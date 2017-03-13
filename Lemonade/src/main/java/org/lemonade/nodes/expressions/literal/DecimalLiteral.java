package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public class DecimalLiteral extends NumericLiteral<Double> implements Comparable<DecimalLiteral> {

    public DecimalLiteral(String value) {
        super(new QLDecimalType(), Double.parseDouble(value));
    }

    public DecimalLiteral(double value) {
        super(new QLDecimalType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public DecimalLiteral plus(IntegerLiteral that) {
        return new DecimalLiteral(this.getValue() + that.getValue());
    }

    public DecimalLiteral plus(DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() + that.getValue());
    }

    public MoneyLiteral plus(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() + that.getValue());
    }

    public DecimalLiteral minus(IntegerLiteral that) {
        return new DecimalLiteral(this.getValue() - that.getValue());
    }

    public DecimalLiteral minus(final DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() - that.getValue());
    }

    public MoneyLiteral minus(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() - that.getValue());
    }

    public DecimalLiteral product(final IntegerLiteral that) {
        return new DecimalLiteral(this.getValue() * that.getValue());
    }

    public DecimalLiteral product(final DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() * that.getValue());
    }

    public MoneyLiteral product(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() * that.getValue());
    }

    public DecimalLiteral divide(final IntegerLiteral that) {
        return new DecimalLiteral((int) (this.getValue() / that.getValue()));
    }

    public DecimalLiteral divide(final DecimalLiteral that) {
        return new DecimalLiteral(this.getValue() / that.getValue());
    }

    public MoneyLiteral divide(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() / that.getValue());
    }

    @Override
    public BooleanLiteral gT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DecimalLiteral) that) == 1);
    }

    @Override
    public BooleanLiteral gTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DecimalLiteral) that) >= 0);
    }

    @Override
    public BooleanLiteral lT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DecimalLiteral) that) == -1);
    }

    @Override
    public BooleanLiteral lTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DecimalLiteral) that) <= 0);
    }

    @Override
    public DecimalLiteral neg() {
        return new DecimalLiteral(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DecimalLiteral)) {
            return false;
        }
        DecimalLiteral that = (DecimalLiteral) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(DecimalLiteral that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }

}
