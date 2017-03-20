/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/statements/Statement.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.visitorinterfaces.FormAndStatementVisitor;

public abstract class Statement extends Node {

    public Statement(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(FormAndStatementVisitor<T> visitor);
}
