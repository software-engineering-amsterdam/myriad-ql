package org.lemonade.nodes.expressions;

import org.lemonade.nodes.expressions.literal.BooleanLiteral;
import org.lemonade.nodes.types.QLType;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public abstract class Literal<T> extends Expression {
    QLType type;
    T value;

    public Literal(QLType type, T value) {
        super();
        this.type = type;
        this.value = value;
    }

    public QLType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public Literal<Boolean> eq(Literal<?> that) {
        return new BooleanLiteral(that.equals(this));
    }

    public Literal<Boolean> nEq(Literal<?> that) {
        return new BooleanLiteral(!that.equals(this));
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
