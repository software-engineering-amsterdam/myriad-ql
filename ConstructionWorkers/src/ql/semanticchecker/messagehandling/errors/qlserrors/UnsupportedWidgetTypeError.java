package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.qlerrors.Error;

/**
 * Created by LGGX on 09-Mar-17.
 */
public class UnsupportedWidgetTypeError extends Error{

    private final String name;

    public UnsupportedWidgetTypeError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Widget assignment of " + name + " at line " +  getLineNumber().getStartingLine() + " is not" +
                " compatible with question type.";
    }
}
