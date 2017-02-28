/**
 * StringType.java.
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.StringValue;

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
    public StringValue getDefaultValue() {
        return new StringValue(" ");
    }
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
