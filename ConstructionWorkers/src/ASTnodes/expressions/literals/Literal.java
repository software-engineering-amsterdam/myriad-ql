/**
 * Literal.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.expressions.Expression;
import ASTnodes.types.Type;
import ASTnodes.CodeLocation;

public abstract class Literal extends Expression {

    public Literal(CodeLocation location) {
        super(location);
    }
}
