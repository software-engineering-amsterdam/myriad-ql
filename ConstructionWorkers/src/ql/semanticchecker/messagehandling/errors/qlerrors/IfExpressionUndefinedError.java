/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlerrors/IfExpressionUndefinedError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.Error;

public class IfExpressionUndefinedError extends Error {

    public IfExpressionUndefinedError(LineNumber lineNumber) {
        super(lineNumber);
    }

    public String getMessage() {
        return "ERROR: Identifier in if-statement condition at line " + getLineNumber().getStartingLine() +
                " in QL form is undefined.";
    }
}
