/**
 * ErrorHandler.java.
 */

package QL.semanticChecker.messageHandling.errors;

import QL.ASTnodes.LineNumber;
import QL.semanticChecker.messageHandling.MessageHandler;

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
