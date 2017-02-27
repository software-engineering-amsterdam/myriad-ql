/**
 * Unary.java.
 */

package QL.ASTnodes.expressions.unaries;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.types.IntegerType;
import QL.ASTnodes.types.MoneyType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.types.UndefinedType;

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
