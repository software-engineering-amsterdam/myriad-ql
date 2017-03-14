package org.uva.taxfree.gui;

import javax.swing.*;
import java.util.List;

public class MessageWindow {

    public static boolean retryDialog(Message message) {
        int choice = JOptionPane.showConfirmDialog(null, message.toString(), "Do you want to continue?", JOptionPane.YES_NO_OPTION);
        return (JOptionPane.YES_OPTION == choice);
    }

    public static void showMessages(MessageList messageList) {
        JOptionPane.showMessageDialog(null,
                messageList.toString(), "Semantic analyzer report", messageType(messageList));
    }

    private static String generateMessage(List<Message> messages) {
        String messageText = new String();
        for (Message message : messages) {
            messageText += message.toString();
        }
        return messageText;
    }

    private static int messageType(MessageList messageList) {
        if (messageList.fatalErrors()) {
            return JOptionPane.ERROR_MESSAGE;
        }
        return JOptionPane.WARNING_MESSAGE;
    }
}
