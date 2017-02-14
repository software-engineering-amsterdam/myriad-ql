/**
 * BooleanType.java.
 */

package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

public class BooleanType extends Type {

    public BooleanType(CodeLocation location) {
        super(location);
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        return visitor.visit(this);
    }
}
