/**
 * Type.java.
 */

package ql.astnodes.types;

import ql.astnodes.Node;
import ql.astnodes.LineNumber;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.Value;

public abstract class Type extends Node {

    public Type() {
        super();
    }

    public Type(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract Value getDefaultValue();

    public String toString() {
        return "Type";
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
