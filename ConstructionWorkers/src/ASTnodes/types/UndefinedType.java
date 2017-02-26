/**
 * UndefinedType.java.
 */

package ASTnodes.types;

import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.UndefinedValue;

public class UndefinedType extends Type {

    public UndefinedType() {
        super();
    }

    @Override
    public String toString() {
        return "Undefined";
    }

    @Override
    public UndefinedValue getDefaultState() {
        return new UndefinedValue();
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        new AssertionError("Undefined type found!");
        return null;
    }
}
