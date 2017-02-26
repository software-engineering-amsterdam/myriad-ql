/**
 * Unary.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.types.IntegerType;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;

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
