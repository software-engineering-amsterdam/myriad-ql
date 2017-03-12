/**
 * Binary.java.
 */

package ql.astnodes.expressions.binaries;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;

public abstract class Binary extends Expression {

    private final Expression left;
    private final Expression right;

    public Binary(Expression left, Expression right, LineNumber lineNumber) {
        super(lineNumber);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
