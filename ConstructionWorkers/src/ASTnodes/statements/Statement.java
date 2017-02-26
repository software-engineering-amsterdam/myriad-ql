/**
 * Statement.java.
 */

package ASTnodes.statements;

import ASTnodes.LineNumber;
import ASTnodes.Node;
import ASTnodes.visitors.FormAndStatementVisitor;

public abstract class Statement extends Node {

    public Statement(LineNumber location) {
        super(location);
    }

    public abstract <T> T accept(FormAndStatementVisitor<T> visitor);
}
