package org.uva.taxfree.ql.gui;

public class WarningMessage extends Message {
    public WarningMessage(String message) {
        super(message);
    }

    @Override
    protected String messagePrefix() {
        return "Warning at ";
    }
}
