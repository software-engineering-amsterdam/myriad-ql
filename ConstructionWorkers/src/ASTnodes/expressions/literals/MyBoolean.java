/**
 * MyBoolean.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.types.Type;
import ASTnodes.LineNumber;
import ASTnodes.types.BooleanType;
import ASTnodes.visitors.ExpressionVisitor;

public class MyBoolean extends Literal {

    private final Type type;
    private final Boolean value;

    public MyBoolean(Boolean value, LineNumber location) {
        super(location);
        this.value = value;
        this.type = new BooleanType(location);
    }

    public Type getType() {
        return type;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
