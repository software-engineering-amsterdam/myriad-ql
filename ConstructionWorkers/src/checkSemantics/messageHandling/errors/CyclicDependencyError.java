package checkSemantics.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 19-Feb-17.
 */
public class CyclicDependencyError extends ErrorHandler{

    private Identifier end;
    private Identifier start;

    public CyclicDependencyError(CodeLocation location, Identifier end, Identifier start) {
        super(location);
        this.end = end;
        this.start = start;
    }

    public String getMessage() {
        String message = "ERROR: Cyclomatic dependency between " + end.getName() + " at " +
                end.getLocation().getStartingLine() + " and " +  start.getName() + " at " +
                start.getLocation().getStartingLine() + ".";

        return message;
    }
}
