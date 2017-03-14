/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/warnings/DuplicateIdentifierWarning.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

public class DuplicateIdentifierWarning extends Warning {

    private final Identifier identifier;

    public DuplicateIdentifierWarning(LineNumber lineNumber, Identifier identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return "WARNING: Question " + identifier.getName() + " at line " + getLineNumber().getStartingLine() +
                " in QL form has a duplicate identifier with the same type.";
    }
}
