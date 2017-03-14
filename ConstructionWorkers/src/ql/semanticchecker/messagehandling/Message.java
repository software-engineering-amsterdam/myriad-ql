/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/Message.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling;

import ql.astnodes.LineNumber;

public abstract class Message {

    private final LineNumber lineNumber;

    public Message(LineNumber lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LineNumber getLineNumber() {
        return lineNumber;
    }

    public abstract String getMessage();
}
