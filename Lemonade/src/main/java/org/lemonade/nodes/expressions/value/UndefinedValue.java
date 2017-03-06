package org.lemonade.nodes.expressions.value;

import jdk.nashorn.internal.runtime.Undefined;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public class UndefinedValue extends Expression {
    QLType type;

    public UndefinedValue(QLType type) {
        this.type = type;
    }

    public QLType getType() {
        return type;
    }

}
