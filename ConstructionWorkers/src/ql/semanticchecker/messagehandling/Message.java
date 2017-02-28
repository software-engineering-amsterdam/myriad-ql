/**
 * Message.java.
 */

package ql.semanticchecker.messagehandling;

import ql.astnodes.LineNumber;

public abstract class Message {

    private final LineNumber lineNumber;

    public Message(LineNumber lineNumber) {
        this.lineNumber = lineNumber;
    }
    public abstract String getMessage();

    public LineNumber getLineNumber() {
        return lineNumber;
    }
}
