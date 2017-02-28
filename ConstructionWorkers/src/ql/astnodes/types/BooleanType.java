/**
 * BooleanType.java.
 */

package ql.astnodes.types;


import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.BooleanValue;

public class BooleanType extends Type {

    public BooleanType() {
        super();
    }

    public BooleanType(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public BooleanValue getDefaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
