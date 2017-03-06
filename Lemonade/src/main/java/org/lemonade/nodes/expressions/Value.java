package org.lemonade.nodes.expressions;

import org.lemonade.nodes.Position;
import org.lemonade.nodes.expressions.value.BooleanValue;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class Value<T> extends Expression {
    QLType type;
    T value;

    public Value(QLType type, T value) {
        this.type = type;
        this.value = value;
    }

    public QLType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public Value<Boolean> eq(Value<?> that) {
        return new BooleanValue(that.equals(this));
    }

    public Value<Boolean> nEq(Value<?> that) {
        return new BooleanValue(!that.equals(this));
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
