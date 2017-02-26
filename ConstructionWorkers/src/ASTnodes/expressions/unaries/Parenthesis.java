/**
 * Parenthesis.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.LineNumber;
import ASTnodes.expressions.Expression;
import ASTnodes.types.*;
import ASTnodes.visitors.ExpressionVisitor;

public class Parenthesis extends Unary {

    public Parenthesis(Expression expression, LineNumber location) {
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
