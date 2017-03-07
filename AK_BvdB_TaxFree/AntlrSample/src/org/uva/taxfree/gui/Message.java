package org.uva.taxfree.gui;

import java.awt.*;

public abstract class Message {
    private final String mMessage;
    private final Color mColor;

    public Message(String message, Color color) {
        mMessage = message;
        mColor = color;
    }
}
