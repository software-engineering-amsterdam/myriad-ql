package org.uva.hatt.taxform.ast.visitors.exceptionHandler.error;

public class Error {
    private String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
