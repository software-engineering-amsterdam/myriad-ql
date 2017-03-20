/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/types/Type.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes.types;

import ql.astnodes.Node;
import ql.astnodes.LineNumber;
import ql.visitorinterfaces.TypeVisitor;

public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Type)) {
            return false;
        }

        Type type = (Type) object;
        return getClass().equals(type.getClass());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String toString() {
        return "Type";
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
