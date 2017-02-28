/**
 * Unary.java.
 */

package ql.astnodes.expressions.unaries;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.MoneyType;
import ql.astnodes.types.Type;
import ql.astnodes.types.UndefinedType;

public abstract class Unary extends Expression {

    private final Expression expression;

    public Unary(Expression expression, LineNumber location) {
        super(location);
        this.expression = expression;
    }

    public Type checkType(Type typeToCheck) {
        if (typeToCheck == null) {
            return new UndefinedType();
        } else {
            if (typeToCheck.getClass().equals(IntegerType.class) || typeToCheck.getClass().equals(MoneyType.class)) {
                return typeToCheck;
            } else {
                return new UndefinedType();
            }
        }
    }

    public Expression getExpression() {
        return expression;
    }
}
