/**
 * CyclicDependencyError.java.
 */

package lexicalChecker.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

public class CyclicDependencyError extends ErrorHandler {

    private final Identifier end;
    private final Identifier start;

    public CyclicDependencyError(CodeLocation location, Identifier end, Identifier start) {
        super(location);
        this.end = end;
        this.start = start;
    }

    public String getMessage() {
        return "ERROR: Cyclomatic dependency between " + end.getName() + " at " + end.getLocation().getStartingLine() +
                " and " +  start.getName() + " at " +  start.getLocation().getStartingLine() + ".";
    }
}
