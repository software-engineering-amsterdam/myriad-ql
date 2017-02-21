/**
 * WarningHandler.java.
 */

package lexicalChecker.messageHandling.warnings;

import ASTnodes.CodeLocation;
import lexicalChecker.messageHandling.MessageHandler;

public abstract class WarningHandler extends MessageHandler {

    private final CodeLocation location;

    public WarningHandler(CodeLocation location) {
        this.location = location;
        this.type = "Warning";
    }

    public CodeLocation getLocation() {
        return location;
    }
}
