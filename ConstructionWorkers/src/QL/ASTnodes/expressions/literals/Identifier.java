/**
 * Identifier.java.
 */

package QL.ASTnodes.expressions.literals;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.visitors.ExpressionVisitor;

public class Identifier extends Literal {

    private final String value;

    public Identifier(String value, LineNumber location) {
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
