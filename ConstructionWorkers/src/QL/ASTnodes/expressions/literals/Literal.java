/**
 * Literal.java.
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.expressions.Expression;
import ql.astnodes.LineNumber;

public abstract class Literal extends Expression {

    public Literal(LineNumber location) {
        super(location);
    }
}
