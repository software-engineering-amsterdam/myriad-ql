/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/binaries/numerical/Division.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.binaries.numerical;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.visitorinterfaces.ExpressionVisitor;

public class Division extends Numerical {

    public Division(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
