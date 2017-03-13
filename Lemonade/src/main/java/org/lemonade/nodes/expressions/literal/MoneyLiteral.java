package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.visitors.ASTVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public class MoneyLiteral extends NumericLiteral<Double> implements Comparable<MoneyLiteral> {

    public MoneyLiteral(String value) {
        super(new QLMoneyType(), Double.parseDouble(value));
    }

    public MoneyLiteral(double value) {
        super(new QLMoneyType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public MoneyLiteral plus(IntegerLiteral that) {
        return new MoneyLiteral(this.getValue() + that.getValue());
    }

    public MoneyLiteral plus(DecimalLiteral that) {
        return new MoneyLiteral(this.getValue() + that.getValue());
    }

    public MoneyLiteral plus(MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() + that.getValue());
    }

    public MoneyLiteral minus(final IntegerLiteral that) {
        return new MoneyLiteral(this.getValue() - that.getValue());
    }

    public MoneyLiteral minus(final DecimalLiteral that) {
        return new MoneyLiteral(this.getValue() - that.getValue());
    }

    public MoneyLiteral minus(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() - that.getValue());
    }

    public MoneyLiteral product(final IntegerLiteral that) {
        return new MoneyLiteral(this.getValue() * that.getValue());
    }

    public MoneyLiteral product(final DecimalLiteral that) {
        return new MoneyLiteral(this.getValue() * that.getValue());
    }

    public MoneyLiteral product(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() * that.getValue());
    }

    public MoneyLiteral divide(final IntegerLiteral that) {
        return new MoneyLiteral((int) (this.getValue() / that.getValue()));
    }

    public MoneyLiteral divide(final DecimalLiteral that) {
        return new MoneyLiteral(this.getValue() / that.getValue());
    }

    public MoneyLiteral divide(final MoneyLiteral that) {
        return new MoneyLiteral(this.getValue() / that.getValue());
    }

    @Override
    public BooleanLiteral gT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((MoneyLiteral) that) == 1);
    }

    @Override
    public BooleanLiteral gTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((MoneyLiteral) that) >= 0);
    }

    @Override
    public BooleanLiteral lT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((MoneyLiteral) that) == -1);
    }

    @Override
    public BooleanLiteral lTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((MoneyLiteral) that) <= 0);
    }

    @Override
    public MoneyLiteral neg() {
        return new MoneyLiteral(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MoneyLiteral)) {
            return false;
        }
        MoneyLiteral that = (MoneyLiteral) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(MoneyLiteral that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
