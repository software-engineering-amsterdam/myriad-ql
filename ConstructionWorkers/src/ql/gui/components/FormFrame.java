/**
 * FormFrame.java.
 */

package ql.gui.components;

import javax.swing.*;

public class FormFrame {

    private final JFrame formFrame;
    private final QuestionPanel panel;

    private static final int DEFAULT_WIN_WIDTH = 480;
    private static final int DEFAULT_WIN_HEIGHT = 640;

    public FormFrame(String formTitle) {
        formFrame = new JFrame(formTitle);
        formFrame.setSize(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
        formFrame.setLocationRelativeTo(null);
        formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formFrame.setResizable(false);

        panel = new QuestionPanel();
        panel.render(formFrame);
    }

    public void showForm() {
        formFrame.setVisible(true);
    }

    public void addWidget(JComponent component) {
        panel.add(component);
        formFrame.revalidate();
    }

    public void removeWidget(JComponent component) {
        panel.remove(component);
        formFrame.revalidate();
    }
}
