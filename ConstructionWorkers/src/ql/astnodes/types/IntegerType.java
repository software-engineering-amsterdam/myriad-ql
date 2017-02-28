/**
 * IntegerType.java.
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.IntegerValue;

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
    public IntegerValue getDefaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
