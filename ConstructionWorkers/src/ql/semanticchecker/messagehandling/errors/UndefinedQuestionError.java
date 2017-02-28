/**
 * UndefinedQuestionError.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class UndefinedQuestionError extends Error {

    private final Identifier identifier;

    public UndefinedQuestionError(LineNumber location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " is undefined.";
    }
}
