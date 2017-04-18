package org.lemonade.nodes.expressions.literal;

import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLType;

/**
 *
 */

public abstract class NumericLiteral<T> extends ComparableLiteral<T> {

    public NumericLiteral(QLType type, T value) {
        super(type, value);
    }
}
