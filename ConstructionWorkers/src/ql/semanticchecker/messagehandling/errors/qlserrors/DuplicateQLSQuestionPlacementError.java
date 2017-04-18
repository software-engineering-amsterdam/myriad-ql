/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/errors/qlserrors/DuplicateQLSQuestionPlacementError.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.Error;

public class DuplicateQLSQuestionPlacementError extends Error {

    private final String name;

    public DuplicateQLSQuestionPlacementError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Duplicate question placement " + name + " at line " +  getLineNumber().getStartingLine() +
                " in QLS stylesheet.";
    }
}
