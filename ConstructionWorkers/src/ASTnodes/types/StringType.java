/**
 * StringType.java.
 */

package ASTnodes.types;

import ASTnodes.CodeLocation;
import ASTnodes.visitors.TypeVisitor;
import semanticChecker.dependency.stateData.stateValues.StringValue;

public class StringType extends Type {

    public StringType() {
        super();
    }

    public StringType(CodeLocation location) {
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
