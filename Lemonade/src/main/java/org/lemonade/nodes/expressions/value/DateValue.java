package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLDateType;

import java.util.Calendar;

/**
 *
 */
public class DateValue extends Value<Calendar> implements Comparable<DateValue>{

    public DateValue(Calendar value) {
        super(new QLDateType(), value);
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
