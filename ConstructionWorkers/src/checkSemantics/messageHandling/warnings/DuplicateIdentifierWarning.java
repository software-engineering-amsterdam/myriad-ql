package checkSemantics.messageHandling.warnings;

import ASTnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class DuplicateIdentifierWarning extends WarningHandler{

    private final Identifier identifier;

    public DuplicateIdentifierWarning(Identifier identifier) {
        super(identifier.getLocation());
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        String message = "WARNING: Question " + identifier.getName() + " at line " + identifier.getLocation().getStartingLine()
                + " has a duplicate identifier with the same type.";

        return message;
    }
}
