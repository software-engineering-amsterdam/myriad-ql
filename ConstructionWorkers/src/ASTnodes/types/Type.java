/**
 * Type.java.
 */

package ASTnodes.types;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.AllVisitors;

public abstract class Type extends Node {

    public Type(CodeLocation location) {
        super(location);
    }

    public abstract <T> T accept(AllVisitors<T> visitor);
}
