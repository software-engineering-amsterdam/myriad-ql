/**
 * MessageHandler.java.
 */

package lexicalChecker.messageHandling;

public abstract class MessageHandler {

    public String type;

    public abstract String getMessage();

    public String getType() {
        return type;
    }
}
