/**
 * IntegerType.java.
 */

package ASTnodes.types;

import ASTnodes.LineNumber;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.IntegerValue;

public class IntegerType extends Type {

    public IntegerType() {
        super();
    }

    public IntegerType(LineNumber location) {
        super(location);
    }

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public IntegerValue getDefaultState() {
        return new IntegerValue(0);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
