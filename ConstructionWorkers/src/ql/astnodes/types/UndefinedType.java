/**
 * UndefinedType.java.
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
