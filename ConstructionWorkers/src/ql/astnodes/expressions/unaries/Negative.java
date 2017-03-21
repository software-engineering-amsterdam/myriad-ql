/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/unaries/Negative.java.
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

public class Negative extends Unary {

    public Negative(Expression expression, LineNumber lineNumber) {
        super(expression, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
