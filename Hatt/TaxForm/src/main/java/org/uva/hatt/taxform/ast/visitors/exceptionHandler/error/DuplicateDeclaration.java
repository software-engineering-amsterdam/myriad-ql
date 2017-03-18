package org.uva.hatt.taxform.ast.visitors.exceptionHandler.error;

public class DuplicateDeclaration extends Error{

    public DuplicateDeclaration(int lineNumber, String message){
        super("Duplicate question declaration at line " + lineNumber + ": declaration: " + message);

    }
}
