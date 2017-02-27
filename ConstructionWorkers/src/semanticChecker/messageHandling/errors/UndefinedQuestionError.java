/**
 * UndefinedQuestionError.java.
 */

package semanticChecker.messageHandling.errors;

import ASTnodes.CodeLocation;
import ASTnodes.expressions.literals.Identifier;

public class UndefinedQuestionError extends ErrorHandler {

    private final Identifier identifier;

    public UndefinedQuestionError(CodeLocation location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " is undefined.";
    }
}
