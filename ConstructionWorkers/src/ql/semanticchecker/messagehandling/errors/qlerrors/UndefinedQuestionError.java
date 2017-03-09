/**
 * UndefinedQuestionError.java.
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class UndefinedQuestionError extends Error {

    private final Identifier identifier;

    public UndefinedQuestionError(LineNumber lineNumber, Identifier identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLineNumber().getStartingLine() +
                " is undefined.";
    }
}
