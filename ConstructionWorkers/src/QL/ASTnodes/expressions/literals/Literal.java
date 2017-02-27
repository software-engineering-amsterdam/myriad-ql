/**
 * Literal.java.
 */

package QL.ASTnodes.expressions.literals;

import QL.ASTnodes.expressions.Expression;
import QL.ASTnodes.LineNumber;

public abstract class Literal extends Expression {

    public Literal(LineNumber location) {
        super(location);
    }
}
