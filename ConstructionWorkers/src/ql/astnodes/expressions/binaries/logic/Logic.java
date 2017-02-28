/**
 * Logic.java.
 */

package ql.astnodes.expressions.binaries.logic;

import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;
import ql.astnodes.LineNumber;

public abstract class Logic extends Binary {

    public Logic(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }
}
