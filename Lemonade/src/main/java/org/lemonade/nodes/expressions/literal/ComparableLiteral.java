package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.types.QLType;

public abstract class ComparableLiteral<T> extends Literal<T> {

    public ComparableLiteral(QLType type, T value) {
        super(type, value);
    }

    public void evaluateType(final ComparableLiteral<?> that) {
        if (!that.getType().isOf(this.getType().getClass())) {
            throw new IllegalArgumentException("Cannot compare " + this.getType().toString() + " with " + that.getType().toString());
        }
    }
}
