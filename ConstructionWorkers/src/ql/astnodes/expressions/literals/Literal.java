/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/literals/Literal.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions.literals;

import ql.astnodes.expressions.Expression;
import ql.astnodes.LineNumber;

abstract class Literal extends Expression {

    Literal(LineNumber lineNumber) {
        super(lineNumber);
    }
}
