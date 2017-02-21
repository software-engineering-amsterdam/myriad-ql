/**
 * DuplicateIdentifierWarning.java.
 */

package lexicalChecker.messageHandling.warnings;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

public class DuplicateIdentifierWarning extends WarningHandler {

    private final Identifier identifier;

    public DuplicateIdentifierWarning(CodeLocation location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "WARNING: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " has a duplicate identifier with the same type.";
    }
}
