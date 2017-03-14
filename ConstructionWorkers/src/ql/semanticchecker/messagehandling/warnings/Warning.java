/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/warnings/Warning.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.warnings;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Warning extends Message {

    private final String type;

    Warning(LineNumber lineNumber) {
        super(lineNumber);
        this.type = "Warning";
    }

    public String getType(){
        return type;
    }
}
