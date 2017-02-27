package org.ql.ast.expression.literal;

import org.ql.ast.Expression;

abstract class AbstractLiteral<T> extends Expression {
    final T value;

    AbstractLiteral(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
