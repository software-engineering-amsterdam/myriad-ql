package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class IntegerLit extends Literal {
    private int value;

    public IntegerLit(String value) {
        this.value = Integer.parseInt(value);
    }
}
