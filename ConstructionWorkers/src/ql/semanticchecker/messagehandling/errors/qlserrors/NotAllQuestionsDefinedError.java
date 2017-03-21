/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlserrors/NotAllQuestionsDefinedError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.Error;

public class NotAllQuestionsDefinedError extends Error{

    private final String name;

    public NotAllQuestionsDefinedError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Question " + name + " from QL form has not been defined in the QLS stylesheet.";
    }
}
