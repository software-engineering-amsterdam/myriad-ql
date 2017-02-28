/**
 * BooleanType.java.
 */

package ASTnodes.types;


import ASTnodes.CodeLocation;
import ASTnodes.visitors.TypeVisitor;

public class BooleanType extends Type {

    public BooleanType() {
        super();
    }

    public BooleanType(CodeLocation location) {
        super(location);
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
