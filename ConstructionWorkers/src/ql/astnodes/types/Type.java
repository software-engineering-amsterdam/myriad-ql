/**
 * Type.java.
 */

package ql.astnodes.types;

import ql.astnodes.Node;
import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;

public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(LineNumber lineNumber) {
        super(lineNumber);
    }

    public boolean equals(Object object) {
        if (!(object instanceof Type) ) {
            return false;
        }

        Type type = (Type) object;
        return getClass().equals(type.getClass());
    }

    public String toString() {
        return "Type";
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
