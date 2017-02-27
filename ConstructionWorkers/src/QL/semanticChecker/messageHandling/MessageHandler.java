/**
 * MessageHandler.java.
 */

package QL.semanticChecker.messageHandling;

public abstract class MessageHandler {

    public String type;

    public abstract String getMessage();

    public String getType() {
        return type;
    }
}
