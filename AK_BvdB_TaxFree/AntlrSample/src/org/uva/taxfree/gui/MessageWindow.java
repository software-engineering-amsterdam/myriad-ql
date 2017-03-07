package org.uva.taxfree.gui;

import javax.swing.*;
import java.util.List;

public class MessageWindow {
    public static void showMessageDialog(List<Message> messages) {
        JOptionPane.showMessageDialog(null,
                generateMessage(messages), "Sematic analyzer report", messageType(messages));


    }

    private static String generateMessage(List<Message> messages) {
        String messageText = new String();
        for (Message message : messages) {
            messageText += message.toString();
        }
        return messageText;
    }

    private static int messageType(List<Message> messages) {
        boolean fatalError = false;
        for (Message m : messages) {
            if (m.isFatal()) {
                return JOptionPane.ERROR_MESSAGE;
            }
        }
        return JOptionPane.WARNING_MESSAGE;
    }
}
