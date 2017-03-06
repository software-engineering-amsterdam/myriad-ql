/**
 * BooleanType.java.
 */

package ql.astnodes.types;


import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;

public class BooleanType extends Type {

    public BooleanType() {
        super();
    }

    public BooleanType(LineNumber lineNumber) {
        super(lineNumber);
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
