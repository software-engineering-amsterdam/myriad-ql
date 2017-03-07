package ql.semanticchecker.messagehandling.errors;

import ql.astnodes.LineNumber;
import ql.astnodes.expressions.literals.Identifier;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class IfExpressionUndefinedError extends Error {

    public IfExpressionUndefinedError(LineNumber lineNumber) {
        super(lineNumber);
    }

    public String getMessage() {
        return "ERROR: Identifier at line " + getLineNumber().getStartingLine() +
                " in IF statement condition is undefined.";
    }
}
