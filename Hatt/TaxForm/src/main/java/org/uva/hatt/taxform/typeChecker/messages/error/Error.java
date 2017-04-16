package org.uva.hatt.taxform.typeChecker.messages.error;

public class Error {
    private String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
