/**
 * WarningHandler.java.
 */

package QL.semanticChecker.messageHandling.warnings;

import QL.ASTnodes.LineNumber;
import QL.semanticChecker.messageHandling.MessageHandler;

public abstract class WarningHandler extends MessageHandler {

    private final LineNumber location;

    public WarningHandler(LineNumber location) {
        this.location = location;
        this.type = "Warning";
    }

    public LineNumber getLocation() {
        return location;
    }
}
