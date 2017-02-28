/**
 * DuplicateIdentifierError.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateIdentifierError extends Error {

    private final Identifier identifier;

    public DuplicateIdentifierError(LineNumber location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " has a duplicate identifier with a different type.";
    }
}
