package checkSemantics.messageHandling.errors;

import ASTnodes.CodeLocation;
import checkSemantics.messageHandling.MessageHandler;

/**
 * Created by LGGX on 17-Feb-17.
 */
public abstract class ErrorHandler extends MessageHandler{
    private final CodeLocation location;

    public ErrorHandler(CodeLocation location) {
        this.location = location;
        this.type = "Error";
    }

    public CodeLocation getLocation() {
        return location;
    }
}
