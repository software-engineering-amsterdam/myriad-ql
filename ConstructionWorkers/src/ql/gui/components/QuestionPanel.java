package ql.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LGGX on 23-Feb-17.
 */
public class QuestionPanel {

    private JPanel panel;

    public QuestionPanel() {
        this.panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        this.panel.setLayout(layout);
    }

    public void render(JFrame formFrame) {
        formFrame.add(this.panel);
    }

    public void add(JComponent component) {
        this.panel.add(component);
    }

    public void remove(JComponent component) {
        this.panel.remove(component);
    }

}
