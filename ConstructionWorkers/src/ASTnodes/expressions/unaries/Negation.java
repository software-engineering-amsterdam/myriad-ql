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
    public Type checkType(Type typeToCheck) {
        if (typeToCheck == null) {
            return new UndefinedType();
        } else {
            if (typeToCheck.getClass().equals(BooleanType.class)) {
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
