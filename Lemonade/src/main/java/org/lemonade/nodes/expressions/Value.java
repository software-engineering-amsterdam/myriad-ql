package org.lemonade.nodes.expressions;

import org.lemonade.nodes.expressions.value.BooleanValue;
import org.lemonade.nodes.expressions.value.UndefinedValue;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class Value<T> extends UndefinedValue {
    T value;

    public Value(QLType type, T value) {
        super(type);
        this.value = value;
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
