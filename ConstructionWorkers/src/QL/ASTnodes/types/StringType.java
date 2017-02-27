/**
 * StringType.java.
 */

package QL.ASTnodes.types;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.visitors.TypeVisitor;
import QL.semanticChecker.formDataStorage.valueData.values.StringValue;

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
