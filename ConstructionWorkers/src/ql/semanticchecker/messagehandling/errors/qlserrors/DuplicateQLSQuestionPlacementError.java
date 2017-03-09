package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.qlerrors.Error;

/**
 * Created by LGGX on 07-Mar-17.
 */
public class DuplicateQLSQuestionPlacementError extends Error {

    private final String name;

    public DuplicateQLSQuestionPlacementError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Duplicate question placement " + name + " at line " +  getLineNumber().getStartingLine() + ".";
    }
}
