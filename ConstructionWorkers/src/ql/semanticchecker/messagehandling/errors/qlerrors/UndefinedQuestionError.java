/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlerrors/UndefinedQuestionError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;
import ql.semanticchecker.messagehandling.errors.Error;

public class UndefinedQuestionError extends Error {

    private final Identifier identifier;

    public UndefinedQuestionError(LineNumber lineNumber, Identifier identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLineNumber().getStartingLine() +
                " in QL form is undefined.";
    }
}
