/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/binaries/numerical/Numerical.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.binaries.numerical;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.Binary;

public abstract class Numerical extends Binary {

    Numerical(Expression left, Expression right, LineNumber lineNumber) {
        super(left, right, lineNumber);
    }
}
