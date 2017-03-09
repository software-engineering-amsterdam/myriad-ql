/**
 * DuplicateIdentifierError.java.
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateIdentifierError extends Error {

    private final Identifier identifier;

    public DuplicateIdentifierError(LineNumber lineNumber, Identifier identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLineNumber().getStartingLine() +
                " has a duplicate identifier with a different type.";
    }
}
