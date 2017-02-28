/**
 * Statement.java.
 */

package ql.astnodes.statements;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import ql.astnodes.visitors.FormAndStatementVisitor;

public abstract class Statement extends Node {

    public Statement(LineNumber location) {
        super(location);
    }

    public abstract <T> T accept(FormAndStatementVisitor<T> visitor);
}
