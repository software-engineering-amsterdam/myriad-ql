package org.ql.typechecker.messages;

public abstract class Message {
    private String message = "An error occured: " + getType()
            + " involving " + getErroneousVariable();

    public abstract String getType();

    public abstract String getErroneousVariable();

    public String getMessage() {
        return message;
    }
}
