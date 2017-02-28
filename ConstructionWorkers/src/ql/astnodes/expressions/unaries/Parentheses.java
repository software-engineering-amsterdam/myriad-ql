/**
 * Parentheses.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.types.*;
import ql.astnodes.visitors.ExpressionVisitor;

public class Parentheses extends Unary {

    public Parentheses(Expression expression, LineNumber location) {
        super(expression, location);
    }

    @Override
    public Type checkType(Type typeToCheck) {
        if (typeToCheck == null) {
            return new UndefinedType();
        } else {
            Class typeToCheckClass = typeToCheck.getClass();

            if (typeToCheckClass.equals(IntegerType.class) || typeToCheckClass.equals(MoneyType.class) ||
                    typeToCheckClass.equals(BooleanType.class) || typeToCheckClass.equals(StringType.class)) {
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
