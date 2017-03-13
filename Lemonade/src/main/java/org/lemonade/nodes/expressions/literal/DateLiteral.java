package org.lemonade.nodes.expressions.literal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.assertj.core.internal.cglib.core.Local;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

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
    public BooleanLiteral gT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DateLiteral) that) >= 1);
    }

    @Override
    public BooleanLiteral gTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DateLiteral) that) >= 0);
    }

    @Override
    public BooleanLiteral lT(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DateLiteral) that) <= -1);
    }

    @Override
    public BooleanLiteral lTEq(final ComparableLiteral<?> that) {
        evaluateType(that);
        return new BooleanLiteral(this.compareTo((DateLiteral) that) <= 0);
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
