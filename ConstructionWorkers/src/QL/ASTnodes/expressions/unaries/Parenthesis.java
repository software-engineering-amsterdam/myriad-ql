/**
 * Parenthesis.java.
 */

package QL.ASTnodes.expressions.unaries;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.types.*;
import QL.ASTnodes.visitors.ExpressionVisitor;

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
