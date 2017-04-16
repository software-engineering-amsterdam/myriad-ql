package org.uva.hatt.taxform.typeChecker.exceptionHandler.error;

public class TypeMismatch extends Error{

    public TypeMismatch(int lineNumber, String message){
        super("Type mismatch at line " + lineNumber + ": expected boolean but got: " + message);
    }
}
