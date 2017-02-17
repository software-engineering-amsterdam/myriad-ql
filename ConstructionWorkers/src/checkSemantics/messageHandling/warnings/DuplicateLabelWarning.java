package checkSemantics.messageHandling.warnings;

import ASTnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 17-Feb-17.
 */
public class DuplicateLabelWarning extends WarningHandler{

    private final Identifier identifier;
    private final String label;

    public DuplicateLabelWarning(Identifier identifier, String label) {
        super(identifier.getLocation());
        this.identifier = identifier;
        this.label = label;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        String message = "WARNING: Question " + identifier.getName() + " at line " + identifier.getLocation().getStartingLine()
                + " has a duplicate label: " + label + ".";

        return message;
    }
}
