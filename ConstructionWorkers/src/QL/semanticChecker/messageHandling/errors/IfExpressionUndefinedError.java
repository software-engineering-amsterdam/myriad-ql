package QL.semanticChecker.messageHandling.errors;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class IfExpressionUndefinedError extends ErrorHandler {

    private final Identifier identifier;

    public IfExpressionUndefinedError(LineNumber location, Identifier identifier) {
        super(location);
        this.identifier = identifier;
    }

    public String getMessage() {
        return "ERROR: Identifier " + identifier.getName() + " at line " + getLocation().getStartingLine() +
                " in IF statement condition is undefined.";
    }
}
