package org.uva.taxfree.gui;

import javax.swing.*;
import java.util.List;

public class MessageWindow {
    public static void showMessageDialog(List<Message> messages) {
        JOptionPane.showConfirmDialog(null,
                "choose one", "choose one", JOptionPane.OK_OPTION);
    }
}
