/**
 * Expression.java.
 */

package ASTnodes.expressions;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

public abstract class Expression extends Node {

    public Expression(CodeLocation location) {
        super(location);
    }

    public abstract <T> T accept(AllVisitors<T> visitor);
}
