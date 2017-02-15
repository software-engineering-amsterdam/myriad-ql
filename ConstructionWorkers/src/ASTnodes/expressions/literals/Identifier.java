/**
 * Identifier.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.ExpressionVisitor;

public class Identifier extends Literal {

    private final String value;

    public Identifier(String value, CodeLocation location) {
        super(location);
        this.value = value;
    }

    public String getName() {
        return this.value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
