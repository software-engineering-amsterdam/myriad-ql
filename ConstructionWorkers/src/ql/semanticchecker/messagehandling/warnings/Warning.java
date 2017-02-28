/**
 * Warning.java.
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Warning extends Message {

    private final String type;

    public Warning(LineNumber lineNumber) {
        super(lineNumber);
        this.type = "Warning";
    }

    public String getType(){
        return type;
    }
}
