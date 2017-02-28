/**
 * CyclicDependencyError.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

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
                " at line " +  getLineNumber().getStartingLine() + ".";
    }
}
