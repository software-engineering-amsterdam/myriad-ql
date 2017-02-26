/**
 * WarningHandler.java.
 */

package semanticChecker.messageHandling.warnings;

import ASTnodes.LineNumber;
import semanticChecker.messageHandling.MessageHandler;

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
