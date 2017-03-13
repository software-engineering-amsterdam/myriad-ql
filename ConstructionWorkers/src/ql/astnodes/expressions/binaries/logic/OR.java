/**
 * OR.java.
 */

package ql.astnodes.expressions.binaries.logic;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.visitorinterfaces.ExpressionVisitor;

public class OR extends Logic {

    public OR(Expression left, Expression right, LineNumber lineNumber) {
        super(left ,right, lineNumber);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
