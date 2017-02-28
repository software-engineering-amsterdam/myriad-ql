/**
 * InvalidTypeError.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;

public class InvalidTypeError extends Error {

    private final Type validType;

    public InvalidTypeError(LineNumber lineNumber, Type validType) {
        super(lineNumber);
        this.validType = validType;
    }

    public String getMessage() {
        return "ERROR: Invalid type at line " + getLineNumber().getStartingLine() + ". Type should be " +
                validType.toString() + ".";
    }
}
