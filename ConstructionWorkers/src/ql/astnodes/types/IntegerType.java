/**
 * IntegerType.java.
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.TypeVisitor;

public class IntegerType extends Type {

    public IntegerType() {
        super();
    }

    public IntegerType(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
