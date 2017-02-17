/**
 * Positive.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.types.IntegerType;
import ASTnodes.types.MoneyType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;
import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public class Positive extends Unary {

    public Positive(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType(Type type) {

        String intTest = new IntegerType().getClass().getName();
        String moneyTest = new MoneyType().getClass().getName();

        String typeString = type.getClass().getName();

        if (typeString == intTest || typeString == moneyTest)
            return type;
        else
            return new UndefinedType();
    }
}
