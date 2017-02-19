package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class Boolean extends Literal {
    private boolean value;

    public Boolean(boolean value) {
        this.value = value;
    }
}
