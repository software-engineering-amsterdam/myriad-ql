/**
 * IntegerType.java.
 */

package ASTnodes.types;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.TypeVisitor;

public class IntegerType extends Type {

    public IntegerType() {
        super();
    }

    public IntegerType(CodeLocation location) {
        super(location);
    }

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
