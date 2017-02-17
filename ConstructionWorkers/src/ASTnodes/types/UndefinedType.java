/**
 * UndefinedType.java.
 */

package ASTnodes.types;

import ASTnodes.visitors.FormAndStatementVisitor;
import ASTnodes.visitors.TypeVisitor;

public class UndefinedType extends Type {

    public UndefinedType() {
        super();
    }

    @Override
    public String toString() {
        return "Undefined";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        new AssertionError("Undefined type found!");
        return null;
    }
}
