package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class String extends Literal {
    private String value;

    public String(String value) {
        this.value = value;
    }
}
