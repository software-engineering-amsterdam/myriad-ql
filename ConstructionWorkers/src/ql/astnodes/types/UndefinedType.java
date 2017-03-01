/**
 * UndefinedType.java.
 */

package ql.astnodes.types;

import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.UndefinedValue;

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
        new AssertionError("Undefined type found!");
        return null;
    }
}
