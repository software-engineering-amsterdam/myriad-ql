package org.uva.hatt.taxform.ast.visitors.exceptionHandler.error;

public class UndefinedReference extends Error{

    public UndefinedReference(int lineNumber, String message) {
        super("Undefined question reference at line " + lineNumber + ": reference: " + message);
    }
}
