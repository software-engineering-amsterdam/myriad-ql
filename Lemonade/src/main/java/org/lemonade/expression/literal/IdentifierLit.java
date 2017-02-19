package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class IdentifierLit extends Literal{
    private String value;

    public IdentifierLit(String value) {
        this.value = value;
    }
}
