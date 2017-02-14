/**
 * Identifier.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

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
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
