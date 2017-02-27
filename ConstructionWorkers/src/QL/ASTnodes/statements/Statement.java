/**
 * Statement.java.
 */

package QL.ASTnodes.statements;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.Node;
import QL.ASTnodes.visitors.FormAndStatementVisitor;

public abstract class Statement extends Node {

    public Statement(LineNumber location) {
        super(location);
    }

    public abstract <T> T accept(FormAndStatementVisitor<T> visitor);
}
