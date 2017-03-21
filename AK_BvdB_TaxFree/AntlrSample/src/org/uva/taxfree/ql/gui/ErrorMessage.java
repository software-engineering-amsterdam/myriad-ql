package org.uva.taxfree.ql.gui;

public class ErrorMessage extends Message {
    public ErrorMessage(String message) {
        super(message);
    }

    @Override
    protected String messagePrefix() {
        return "Error";
    }

    @Override
    public boolean isFatal() {
        return true;
    }
}
