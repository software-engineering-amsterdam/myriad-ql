package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class DecimalLit extends Literal {
    private double value;

    public DecimalLit(String value) {
        this.value = Double.parseDouble(value);
    }

}
