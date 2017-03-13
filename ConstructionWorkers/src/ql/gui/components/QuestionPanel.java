/**
 * QuestionPanel.java.
 */

package ql.gui.components;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel {

    private JPanel panel;

    public QuestionPanel() {
        panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        panel.setLayout(layout);
    }

    public void render(JFrame formFrame) {
        formFrame.add(panel);
    }

    public void add(JComponent component) {
        panel.add(component);
    }

    public void remove(JComponent component) {
        panel.remove(component);
    }
}
