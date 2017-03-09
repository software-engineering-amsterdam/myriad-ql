/**
 * Error.java.
 */

package ql.semanticchecker.messagehandling.errors.qlerrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Error extends Message {

    private final String type;

    public Error(LineNumber lineNumber) {
        super(lineNumber);
        this.type = "Error";
    }

    public String getType(){
        return type;
    }
}
