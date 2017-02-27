/**
 * Negation.java.
 */

package QL.ASTnodes.expressions.unaries;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.types.BooleanType;
import QL.ASTnodes.types.Type;
import QL.ASTnodes.types.UndefinedType;
import QL.ASTnodes.visitors.ExpressionVisitor;
import QL.ASTnodes.expressions.Expression;

public class Negation extends Unary {

    public Negation(Expression expression, LineNumber location) {
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
