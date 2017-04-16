package org.uva.hatt.taxform.typechecker.messages.error;

public class InvalidOperandsTypeToOperator extends Error{

    public InvalidOperandsTypeToOperator(int lineNumber, String message){
        super("Invalid operand type at line " + lineNumber + ", to operation: " + message);
    }
}
