package org.lemonade.nodes.expressions.value;

import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLType;

public abstract class ComparableValue<T> extends Value<T> {

    public ComparableValue(QLType type, T value) {
        super(type, value);
    }

    public abstract BooleanValue gT(final ComparableValue<?> that);

    public abstract BooleanValue gTEq(final ComparableValue<?> that);

    public abstract BooleanValue lT(final ComparableValue<?> that);

    public abstract BooleanValue lTEq(final ComparableValue<?> that);

    public void evaluateType(final ComparableValue<?> that) {
        if (!that.getType().isOf(this.getType().getClass())) {
            throw new IllegalArgumentException("Cannot compare " + this.getType().toString() + " with " + that.getType().toString());
        }
    }
}
