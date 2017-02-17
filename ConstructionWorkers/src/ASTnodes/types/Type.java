/**
 * Type.java.
 */

package ASTnodes.types;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.visitors.TypeVisitor;

public abstract class Type extends Node {

    public Type() {
        super();
    }
    public Type(CodeLocation location) {
        super(location);
    }

    public String toString() {
        return "Type";
    }
    public abstract <T> T accept(TypeVisitor<T> visitor);
}
