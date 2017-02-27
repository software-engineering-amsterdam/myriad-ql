/**
 * UndefinedQuestionError.java.
 */

package QL.semanticChecker.messageHandling.errors;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Identifier;

public class UndefinedQuestionError extends ErrorHandler {

    private final Identifier identifier;

    public UndefinedQuestionError(LineNumber location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Question " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " is undefined.";
    }
}
