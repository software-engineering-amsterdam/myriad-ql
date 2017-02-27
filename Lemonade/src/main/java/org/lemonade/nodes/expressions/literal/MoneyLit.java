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
public class MoneyLit extends NumericLit<Double> implements Comparable<MoneyLit> {

    public MoneyLit(QLType type, String value) {
        super(type, Double.parseDouble(value));
        assert type instanceof QLMoneyType;
    }

    public MoneyLit(QLMoneyType type, double value) {
        super(type, value);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public MoneyLit plus(IntegerLit that) {
        return new MoneyLit(new QLMoneyType(), this.getValue() + that.getValue());
    }

    public DecimalLit plus(DecimalLit that) {
        return new DecimalLit(new QLDecimalType(), this.getValue() + that.getValue());
    }

    public MoneyLit plus(MoneyLit that) {
        return new MoneyLit(new QLMoneyType(), this.getValue() + that.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MoneyLit)){
            return false;
        }
        MoneyLit that = (MoneyLit) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(MoneyLit that) {
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
