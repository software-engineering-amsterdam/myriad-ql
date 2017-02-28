package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLType;

import java.util.Date;

/**
 *
 */
public class DateValue extends Value<Date> implements Comparable<DateValue>{

    public DateValue(QLType type, Date value) {
        super(type, value);
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public int compareTo(DateValue that) {
        return this.getValue().compareTo(that.getValue());
    }

    public boolean equals(Object obj){
        if (!(obj instanceof DateValue)) {
            return false;
        }
        DateValue that = (DateValue) obj;

        return this.getValue().equals(that.getValue());
    }
}
