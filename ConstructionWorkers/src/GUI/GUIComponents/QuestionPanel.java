package GUI.GUIComponents;

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

    public void render(JFrame _rootContainer) {
        _rootContainer.add(this.panel);
    }

    public void add(JComponent _component) {
        this.panel.add(_component);
    }

    public void remove(JComponent _component) {
        this.panel.remove(_component);
    }

}
