/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/binaries/logic/Logic.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.binaries.logic;

import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;
import ql.astnodes.LineNumber;

public abstract class Logic extends Binary {

    Logic(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }
}
