/**
 * DuplicateLabelWarning.java.
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateLabelWarning extends Warning {

    private final Identifier identifier;
    private final String label;

    public DuplicateLabelWarning(LineNumber location, Identifier identifier, String label) {
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
