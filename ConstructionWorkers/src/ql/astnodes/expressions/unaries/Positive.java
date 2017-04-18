/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/unaries/Positive.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.unaries;

import ql.visitorinterfaces.ExpressionVisitor;
import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public class Positive extends Unary {

    public Positive(Expression expression, LineNumber lineNumber) {
        super(expression, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
