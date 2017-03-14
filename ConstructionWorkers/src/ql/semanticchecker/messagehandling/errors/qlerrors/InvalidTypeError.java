/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlerrors/InvalidTypeError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import ql.semanticchecker.messagehandling.errors.Error;

public class InvalidTypeError extends Error {

    private final Type validType;

    public InvalidTypeError(LineNumber lineNumber, Type validType) {
        super(lineNumber);
        this.validType = validType;
    }

    public String getMessage() {
        return "ERROR: Invalid type at line " + getLineNumber().getStartingLine() + " in QL form. Type should be " +
                validType.toString() + ".";
    }
}
