/**
 * InvalidTypeError.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;

public class InvalidTypeError extends Error {

    private final Type validType;

    public InvalidTypeError(LineNumber location, Type validType) {
        super(location);
        this.validType = validType;
    }

    public Type getValidType() {
        return validType;
    }

    public String getMessage() {
        return "ERROR: Invalid type at line " + getLocation().getStartingLine() + ". Type should be " +
                validType.toString() + ".";
    }
}
