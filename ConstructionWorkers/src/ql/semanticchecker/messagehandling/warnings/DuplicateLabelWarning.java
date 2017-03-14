/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/warnings/DuplicateLabelWarning.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateLabelWarning extends Warning {

    private final Identifier identifier;
    private final String label;

    public DuplicateLabelWarning(LineNumber lineNumber, Identifier identifier, String label) {
        super(lineNumber);
        this.identifier = identifier;
        this.label = label;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "WARNING: Question " + identifier.getName() + " at line " + getLineNumber().getStartingLine() +
                " in QL form has a duplicate label: " + label + ".";
    }
}
