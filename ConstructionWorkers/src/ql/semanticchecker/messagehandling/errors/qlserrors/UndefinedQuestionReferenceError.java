package ql.semanticchecker.messagehandling.errors.qlserrors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;
import ql.semanticchecker.messagehandling.errors.Error;

/**
 * Created by LGGX on 07-Mar-17.
 */
public class UndefinedQuestionReferenceError extends Error {

    private final String name;

    public UndefinedQuestionReferenceError (LineNumber lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public String getMessage() {
        return "ERROR: Reference to undefined question in QLS code for " + name +
                " at line " +  getLineNumber().getStartingLine() + ".";
    }
}
