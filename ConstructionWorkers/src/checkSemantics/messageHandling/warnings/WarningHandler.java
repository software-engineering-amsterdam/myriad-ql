package checkSemantics.messageHandling.warnings;

import ASTnodes.CodeLocation;
import checkSemantics.messageHandling.MessageHandler;

/**
 * Created by LGGX on 17-Feb-17.
 */
public abstract class WarningHandler extends MessageHandler{

    private final CodeLocation location;

    public WarningHandler(CodeLocation location) {
        this.location = location;
        this.type = "Warning";
    }

    public CodeLocation getLocation() {
        return location;
    }
}
