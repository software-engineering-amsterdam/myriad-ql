/**
 * Statement.java.
 */

package ASTnodes.statements;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

public abstract class Statement extends Node {

    public Statement(CodeLocation location) {
        super(location);
    }

    public abstract <T> T accept(AllVisitors<T> visitor);
}
