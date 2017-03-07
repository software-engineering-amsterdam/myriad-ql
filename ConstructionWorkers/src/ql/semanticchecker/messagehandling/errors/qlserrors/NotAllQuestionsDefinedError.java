package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.semanticchecker.messagehandling.errors.Error;

/**
 * Created by LGGX on 07-Mar-17.
 */
public class NotAllQuestionsDefinedError extends Error{

    private final String name;

    public NotAllQuestionsDefinedError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Question " + name + " from QL has not been defined in the QLS stylesheet.";
    }
}
