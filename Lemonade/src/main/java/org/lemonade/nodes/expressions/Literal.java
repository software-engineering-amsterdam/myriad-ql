package org.lemonade.nodes.expressions;

import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class Literal extends Expression {
    QLType type;

    public Literal(QLType type) {
        super();
        this.type = type;
    }

    public QLType getType() {
        return type;
    }
}
