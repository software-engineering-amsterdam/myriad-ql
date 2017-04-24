package org.uva.hatt.taxform.typechecker.messages.error;

public class UndefinedReference extends Error{

    public UndefinedReference(int lineNumber, String message) {
        super("Undefined question reference at line " + lineNumber + ": reference: " + message);
    }
}
