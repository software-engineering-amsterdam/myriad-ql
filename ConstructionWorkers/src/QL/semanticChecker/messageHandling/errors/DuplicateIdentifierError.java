/**
 * DuplicateIdentifierError.java.
 */

package QL.semanticChecker.messageHandling.errors;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Identifier;

public class DuplicateIdentifierError extends ErrorHandler {

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
