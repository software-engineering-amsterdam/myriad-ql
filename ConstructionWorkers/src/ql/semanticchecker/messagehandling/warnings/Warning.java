/**
 * Warning.java.
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Warning extends Message {

    private final LineNumber location;

    public Warning(LineNumber location) {
        this.location = location;
        this.type = "Warning";
    }

    public LineNumber getLocation() {
        return location;
    }
}
