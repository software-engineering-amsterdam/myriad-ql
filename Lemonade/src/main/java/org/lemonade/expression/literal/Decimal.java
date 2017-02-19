package org.lemonade.expression.literal;

import org.lemonade.expression.Literal;

/**
 *
 */
public class Decimal extends Literal {
    private double value;

    public Decimal(double value) {
        this.value = value;
    }
}
