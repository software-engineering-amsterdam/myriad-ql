/**
 * Negation.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import ql.astnodes.types.UndefinedType;
import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, LineNumber location) {
        super(expression, location);
    }

    @Override
    public Type checkType(Type typeToCheck) {
        if (typeToCheck == null) {
            return new UndefinedType();
        } else {
            if (typeToCheck.getClass().equals(BooleanType.class)) {
                return typeToCheck;
            } else {
                return new UndefinedType();
            }
        }
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
