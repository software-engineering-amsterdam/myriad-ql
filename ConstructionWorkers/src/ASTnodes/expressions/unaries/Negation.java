/**
 * Negation.java.
 */

package ASTnodes.expressions.unaries;

import ASTnodes.types.BooleanType;
import ASTnodes.types.Type;
import ASTnodes.types.UndefinedType;
import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.CodeLocation;
import ASTnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, CodeLocation location) {
        super(expression, location);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType(Type type) {

        String booleanTest = new BooleanType().getClass().getName();

        String typeString = type.getClass().getName();

        if (typeString == booleanTest)
            return type;
        else
            return new UndefinedType();
    }
}
