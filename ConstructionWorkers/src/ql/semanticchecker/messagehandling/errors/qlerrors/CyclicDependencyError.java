/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlerrors/CyclicDependencyError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;
import ql.semanticchecker.messagehandling.errors.Error;

public class CyclicDependencyError extends Error {

    private final Identifier end;
    private final Identifier start;

    public CyclicDependencyError(LineNumber lineNumber, Identifier end, Identifier start) {
        super(lineNumber);
        this.end = end;
        this.start = start;
    }

    public String getMessage() {
        return "ERROR: Cyclomatic dependency between " + end.getName() + " and " +  start.getName() +
                " at line " +  getLineNumber().getStartingLine() + " in QL form.";
    }
}
