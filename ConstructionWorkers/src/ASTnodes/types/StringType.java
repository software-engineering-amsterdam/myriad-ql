/**
 * StringType.java.
 */

package ASTnodes.types;

import ASTnodes.LineNumber;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.formDataStorage.valueData.values.StringValue;

public class StringType extends Type {

    public StringType() {
        super();
    }

    public StringType(LineNumber location) {
        super(location);
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public StringValue getDefaultState() {
        return new StringValue(" ");
    }
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
