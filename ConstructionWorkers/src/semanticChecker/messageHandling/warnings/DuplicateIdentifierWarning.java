/**
 * DuplicateIdentifierWarning.java.
 */

package semanticChecker.messageHandling.warnings;

import ASTnodes.LineNumber;
import ASTnodes.expressions.literals.Identifier;

public class DuplicateIdentifierWarning extends WarningHandler {

    private final Identifier identifier;

    public DuplicateIdentifierWarning(LineNumber location, Identifier identifier) {
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
