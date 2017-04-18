/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/types/BooleanType.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.TypeVisitor;

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
