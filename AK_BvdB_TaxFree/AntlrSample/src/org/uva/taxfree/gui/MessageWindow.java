package org.uva.taxfree.gui;

import javax.swing.*;
import java.util.List;

public class MessageWindow {

    public static boolean retryDialog(Message message) {
        int choice = JOptionPane.showConfirmDialog(null, message.toString(), "Do you want to continue?", JOptionPane.YES_NO_OPTION);
        return (JOptionPane.YES_OPTION == choice);
    }

    public static void showMessages(List<Message> messages) {
        JOptionPane.showMessageDialog(null,
                generateMessage(messages), "Semantic analyzer report", messageType(messages));
    }

    private static String generateMessage(List<Message> messages) {
        String messageText = new String();
        for (Message message : messages) {
            messageText += message.toString();
        }
        return messageText;
    }

    private static int messageType(List<Message> messages) {
        for (Message m : messages) {
            if (m.isFatal()) {
                return JOptionPane.ERROR_MESSAGE;
            }
        }
        return JOptionPane.WARNING_MESSAGE;
    }
}
