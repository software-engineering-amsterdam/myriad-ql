package org.uva.hatt.taxform.typechecker.messages.error;

public class DuplicateDeclaration extends Error{

    public DuplicateDeclaration(int lineNumber, String message){
        super("Duplicate question declaration at line " + lineNumber + ": declaration: " + message);

    }
}
