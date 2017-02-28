/**
 * DuplicateIdentifierWarning.java.
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateIdentifierWarning extends Warning {

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
