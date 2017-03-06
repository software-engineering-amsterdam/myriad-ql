package org.uva.hatt.taxform.ast.visitors;


import java.text.MessageFormat;

public class Error {
    private String message;

    public Error(ErrorType errorType, int lineNumber, String message) {
        message = MessageFormat.format(ErrorType.valueOf(errorType.toString()).toString(), Integer.toString(lineNumber), message);
    }

    public String getMessage() {
        return message;
    }
}
