/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/types/UndefinedType.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.types;

import ql.visitorinterfaces.TypeVisitor;

public class UndefinedType extends Type {

    public UndefinedType() {
        super();
    }

    @Override
    public String toString() {
        return "Undefined";
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return null;
    }
}
