/**
 * UndefinedType.java.
 */

package QL.ASTnodes.types;

import QL.ASTnodes.visitors.TypeVisitor;
import QL.semanticChecker.formDataStorage.valueData.values.UndefinedValue;

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
