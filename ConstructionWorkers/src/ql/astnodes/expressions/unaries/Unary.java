/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/unaries/Unary.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public abstract class Unary extends Expression {

    private final Expression expression;

    Unary(Expression expression, LineNumber lineNumber) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
