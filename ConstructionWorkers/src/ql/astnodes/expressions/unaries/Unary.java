/**
 * Unary.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public abstract class Unary extends Expression {

    private final Expression expression;

    public Unary(Expression expression, LineNumber lineNumber) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
