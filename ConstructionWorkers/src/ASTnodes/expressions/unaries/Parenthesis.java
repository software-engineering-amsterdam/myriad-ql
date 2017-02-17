/**
 * Parenthesis.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;
import ASTnodes.types.*;
import ASTnodes.visitors.ExpressionVisitor;

public class Parenthesis extends Unary {

    public Parenthesis(Expression expression, CodeLocation location) {
        super(expression, location);
    }


    @Override
    public Type getType(Type type) {

        String intTest = new IntegerType().getClass().getName();
        String moneyTest = new MoneyType().getClass().getName();
        String booleanTest = new BooleanType().getClass().getName();
        String stringTest = new StringType().getClass().getName();

        String typeString = type.getClass().getName();
        if (typeString == intTest || typeString == moneyTest || typeString == booleanTest || typeString == stringTest)
            return type;
        else
            return new UndefinedType();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
