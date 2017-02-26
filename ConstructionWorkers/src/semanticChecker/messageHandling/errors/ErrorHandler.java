/**
 * ErrorHandler.java.
 */

package semanticChecker.messageHandling.errors;

import ASTnodes.LineNumber;
import semanticChecker.messageHandling.MessageHandler;

public abstract class ErrorHandler extends MessageHandler {

    private final LineNumber location;

    public ErrorHandler(LineNumber location) {
        this.location = location;
        this.type = "Error";
    }

    public LineNumber getLocation() {
        return location;
    }
}
