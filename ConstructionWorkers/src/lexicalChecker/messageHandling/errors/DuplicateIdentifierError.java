/**
 * DuplicateIdentifierError.java.
 */

package lexicalChecker.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

public class DuplicateIdentifierError extends ErrorHandler {

    private final Identifier identifier;

    public DuplicateIdentifierError(CodeLocation location, Identifier identifier) {
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
