/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/expressions/Expression.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.expressions;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.visitorinterfaces.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(ExpressionVisitor<T> visitor);
}
