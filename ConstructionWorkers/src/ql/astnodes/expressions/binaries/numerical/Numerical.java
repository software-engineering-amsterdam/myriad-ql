/**
 * Numerical.java.
 */

package ql.astnodes.expressions.binaries.numerical;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;

public abstract class Numerical extends Binary {

    public Numerical(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }
}
