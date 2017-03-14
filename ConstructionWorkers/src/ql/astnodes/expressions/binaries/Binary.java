/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/binaries/Binary.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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
