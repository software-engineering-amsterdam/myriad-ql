package org.uva.taxfree.gui;

public abstract class Message {
    private final String mMessage;

    public Message(String message) {
        mMessage = message;
    }

    public String toString() {
        return messagePrefix() + ": " + mMessage + "\n";
    }

    public boolean isFatal() {
        return false;
    }

    protected abstract String messagePrefix();
}

