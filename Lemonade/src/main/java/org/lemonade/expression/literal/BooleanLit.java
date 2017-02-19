package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class BooleanLit extends Literal {
    private boolean value;

    public BooleanLit(String value) {
        this.value = Boolean.parseBoolean(value);
    }

}
