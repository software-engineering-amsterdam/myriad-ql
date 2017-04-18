package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLDateType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

import java.time.LocalDate;

/**
 *
 */
public class DateLiteral extends ComparableLiteral<LocalDate> implements Comparable<DateLiteral> {

    public DateLiteral(LocalDate value) {
        super(new QLDateType(), value);
    }

    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }


    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public int compareTo(DateLiteral that) {
        return this.getValue().compareTo(that.getValue());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DateLiteral)) {
            return false;
        }
        DateLiteral that = (DateLiteral) obj;

        return this.getValue().equals(that.getValue());
    }

}
