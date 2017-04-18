/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/Error.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.Message;

public abstract class Error extends Message {

    private final String type;

    public Error(LineNumber lineNumber) {
        super(lineNumber);
        type = "Error";
    }

    public String getType(){
        return type;
    }
}
