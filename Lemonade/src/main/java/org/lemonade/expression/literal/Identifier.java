package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class Identifier extends Literal{
    private String value;

    public Identifier(String value) {
        this.value = value;
    }
}
