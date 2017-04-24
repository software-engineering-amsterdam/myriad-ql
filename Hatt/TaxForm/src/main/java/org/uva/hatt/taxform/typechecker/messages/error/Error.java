package org.uva.hatt.taxform.typechecker.messages.error;

public class Error {
    private final String msg;

    Error(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
