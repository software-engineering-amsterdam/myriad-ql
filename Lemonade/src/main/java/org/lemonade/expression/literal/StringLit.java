package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class StringLit extends Literal {
    String value;

    public StringLit(String value) {
        this.value = value;
    }
}
