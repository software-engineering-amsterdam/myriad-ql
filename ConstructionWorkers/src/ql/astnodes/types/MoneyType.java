/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/types/MoneyType.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.types;

import ql.astnodes.LineNumber;
import ql.visitorinterfaces.TypeVisitor;

public class MoneyType extends Type {

    public MoneyType() {
        super();
    }

    public MoneyType(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public String toString() {
        return "Money";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
