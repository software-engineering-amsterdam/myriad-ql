package org.uva.taxfree.ql.gui;

public abstract class Message {
    private final String mMessage;

    public Message(String message) {
        assert 0 < message.length();
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

