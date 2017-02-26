/**
 * Literal.java.
 */

package ASTnodes.expressions.literals;

import ASTnodes.expressions.Expression;
import ASTnodes.LineNumber;

public abstract class Literal extends Expression {

    public Literal(LineNumber location) {
        super(location);
    }
}
