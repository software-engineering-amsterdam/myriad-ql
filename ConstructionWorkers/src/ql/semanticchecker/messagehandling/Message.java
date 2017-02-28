/**
 * Message.java.
 */

package ql.semanticchecker.messagehandling;

public abstract class Message {

    public String type;

    public abstract String getMessage();

    public String getType() {
        return type;
    }
}
