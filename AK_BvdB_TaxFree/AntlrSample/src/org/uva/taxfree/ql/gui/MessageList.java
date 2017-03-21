package org.uva.taxfree.ql.gui;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private final List<Message> mMessages;

    public MessageList() {
        mMessages = new ArrayList<>();
    }

    public boolean hasMessages() {
        return 0 < messageAmount();
    }

    public int messageAmount() {
        return mMessages.size();
    }

    public boolean fatalErrors() {
        for (Message m : mMessages) {
            if (m.isFatal()) {
                return true;
            }
        }
        return false;
    }

    public void addError(String message) {
        mMessages.add(new ErrorMessage(message));
    }

    public void addWarning(String message) {
        mMessages.add(new WarningMessage(message));
    }

    @Override
    public String toString() {
        String totalMessages = "";
        for (Message m : mMessages) {
            totalMessages += m.toString();
        }
        return totalMessages;
    }
}
