package org.lemonade.nodes.expressions.value;

import java.time.LocalDate;

import org.lemonade.nodes.types.QLDateType;

/**
 *
 */
public class DateValue extends ComparableValue<LocalDate> implements Comparable<DateValue> {

    public DateValue(LocalDate value) {
        super(new QLDateType(), value);
    }

    @Override
    public BooleanValue gT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DateValue) that) >= 1);
    }

    @Override
    public BooleanValue gTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DateValue) that) >= 0);
    }

    @Override
    public BooleanValue lT(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DateValue) that) <= -1);
    }

    @Override
    public BooleanValue lTEq(final ComparableValue<?> that) {
        evaluateType(that);
        return new BooleanValue(this.compareTo((DateValue) that) <= 0);
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public int compareTo(DateValue that) {
        return this.getValue().compareTo(that.getValue());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DateValue)) {
            return false;
        }
        DateValue that = (DateValue) obj;

        return this.getValue().equals(that.getValue());
    }
}
