/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/types/IntegerType.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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
