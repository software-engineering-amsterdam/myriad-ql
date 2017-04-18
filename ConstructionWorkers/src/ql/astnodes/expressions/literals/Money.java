/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/literals/Money.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.literals;

import java.math.BigDecimal;
import ql.astnodes.LineNumber;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.Type;
import ql.visitorinterfaces.ExpressionVisitor;

public class Money extends Literal {

    private final Type type;
    private final BigDecimal value;

    public Money(BigDecimal value, LineNumber lineNumber) {
        super(lineNumber);
        this.type = new MoneyType(lineNumber);
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
