/**
 * Type.java.
 */

package ASTnodes.types;

import ASTnodes.Node;
import ASTnodes.CodeLocation;
import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.dependency.stateData.stateValues.StateValue;

public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(CodeLocation location) {
        super(location);
    }

    public abstract StateValue getDefaultState();

    public String toString() {
        return "Type";
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
