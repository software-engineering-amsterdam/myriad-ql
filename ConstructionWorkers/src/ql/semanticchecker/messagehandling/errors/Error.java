/**
 * Error.java.
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Error extends Message {

    private final LineNumber location;

    public Error(LineNumber location) {
        this.location = location;
        this.type = "Error";
    }

    public LineNumber getLocation() {
        return location;
    }
}
