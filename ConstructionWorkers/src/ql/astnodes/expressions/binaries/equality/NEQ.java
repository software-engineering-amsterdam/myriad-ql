/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/binaries/equality/NEQ.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.binaries.equality;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.visitorinterfaces.ExpressionVisitor;

public class NEQ extends Equality {

    public NEQ(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
