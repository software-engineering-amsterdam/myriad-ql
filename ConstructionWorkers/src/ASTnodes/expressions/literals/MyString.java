/**
 * MyString.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.types.Type;
import ASTnodes.LineNumber;
import ASTnodes.types.StringType;
import ASTnodes.visitors.ExpressionVisitor;

public class MyString extends Literal {

    private final Type type;
    private final String value;

    public MyString(String value, LineNumber location) {
        super(location);
        this.value = value;
        this.type = new StringType(location);
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
