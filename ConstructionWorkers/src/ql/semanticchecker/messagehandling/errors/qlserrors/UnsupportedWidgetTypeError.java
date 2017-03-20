/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlserrors/UnsupportedWidgetTypeError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.Error;

public class UnsupportedWidgetTypeError extends Error{

    private final String name;

    public UnsupportedWidgetTypeError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Widget assignment of " + name + " at line " +  getLineNumber().getStartingLine() +
                " in QLS stylesheet is not compatible with question type.";
    }
}
