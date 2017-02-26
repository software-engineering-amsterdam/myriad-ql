/**
 * Type.java.
 */

package ASTnodes.types;

import ASTnodes.Node;
import ASTnodes.LineNumber;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.Value;

public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(LineNumber location) {
        super(location);
    }

    public abstract Value getDefaultState();

    public String toString() {
        return "Type";
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
