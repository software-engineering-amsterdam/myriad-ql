/**
 * BooleanType.java.
 */

package QL.ASTnodes.types;


import QL.ASTnodes.LineNumber;
import QL.ASTnodes.visitors.TypeVisitor;
import QL.semanticChecker.formDataStorage.valueData.values.BooleanValue;

public class BooleanType extends Type {

    public BooleanType() {
        super();
    }

    public BooleanType(LineNumber location) {
        super(location);
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public BooleanValue getDefaultState() {
        return new BooleanValue(false);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
