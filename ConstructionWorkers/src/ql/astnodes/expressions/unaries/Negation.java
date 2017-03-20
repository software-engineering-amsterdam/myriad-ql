/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/unaries/Negation.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.ExpressionVisitor;
import ql.astnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, LineNumber lineNumber) {
        super(expression, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
