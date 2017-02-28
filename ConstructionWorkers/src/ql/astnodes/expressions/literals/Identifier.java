/**
 * Identifier.java.
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.LineNumber;
import ql.astnodes.visitors.ExpressionVisitor;

public class Identifier extends Literal {

    private final String name;

    public Identifier(String name, LineNumber lineNumber) {
        super(lineNumber);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
