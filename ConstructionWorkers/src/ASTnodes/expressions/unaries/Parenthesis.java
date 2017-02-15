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

        IntegerType intTest = new IntegerType();
        MoneyType moneyTest = new MoneyType();
        BooleanType booleanTest = new BooleanType();
        StringType stringTest = new StringType();

        if (type.equals(intTest) || type.equals(moneyTest) || type.equals(booleanTest) || type.equals(stringTest))
            return type;
        else
            return new UndefinedType();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
