/**
 * UndefinedType.java.
 */

package ASTnodes.types;

import ASTnodes.visitors.AllVisitors;
import ASTnodes.CodeLocation;

public class UndefinedType extends Type {

    public UndefinedType(CodeLocation location) {
        super(location);
    }

    @Override
    public <T> T accept(AllVisitors<T> visitor) {
        new AssertionError("Undefined type with no accept was accessed.");
        return null;
    }
}
