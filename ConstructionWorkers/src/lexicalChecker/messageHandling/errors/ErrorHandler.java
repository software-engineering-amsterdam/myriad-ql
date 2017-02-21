/**
 * ErrorHandler.java.
 */

package lexicalChecker.messageHandling.errors;

import ASTnodes.CodeLocation;
import lexicalChecker.messageHandling.MessageHandler;

public abstract class ErrorHandler extends MessageHandler {

    private final CodeLocation location;

    public ErrorHandler(CodeLocation location) {
        this.location = location;
        this.type = "Error";
    }

    public CodeLocation getLocation() {
        return location;
    }
}
