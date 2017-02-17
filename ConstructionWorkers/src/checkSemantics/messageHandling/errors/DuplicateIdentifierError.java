package checkSemantics.messageHandling.errors;

import ASTnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class DuplicateIdentifierError extends ErrorHandler{
    private final Identifier identifier;

    public DuplicateIdentifierError(Identifier identifier) {
        super(identifier.getLocation());
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        String message = "ERROR: Question " + identifier.getName() + " at line " + identifier.getLocation().getStartingLine()
                + " has a duplicate identifier with a different type.";

        return message;
    }
}
