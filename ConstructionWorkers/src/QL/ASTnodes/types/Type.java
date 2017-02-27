/**
 * Type.java.
 */

package QL.ASTnodes.types;

import QL.ASTnodes.Node;
import QL.ASTnodes.LineNumber;
import QL.ASTnodes.visitors.TypeVisitor;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

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
