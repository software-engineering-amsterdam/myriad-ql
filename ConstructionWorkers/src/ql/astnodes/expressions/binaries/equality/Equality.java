/**
 * Equality.java
 */

package ql.astnodes.expressions.binaries.equality;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;

public abstract class Equality extends Binary {

    public Equality(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }
}
