/**
 * DuplicateLabelWarning.java.
 */

package lexicalChecker.messageHandling.warnings;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

public class DuplicateLabelWarning extends WarningHandler {

    private final Identifier identifier;
    private final String label;

    public DuplicateLabelWarning(CodeLocation location, Identifier identifier, String label) {
        super(location);
        this.identifier = identifier;
        this.label = label;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "WARNING: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " has a duplicate label: " + label + ".";
    }
}
