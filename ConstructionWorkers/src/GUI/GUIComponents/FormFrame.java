package GUI.GUIComponents;

import javax.swing.*;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class FormFrame {

    private static final int DEFAULT_WIN_HEIGHT = 640;
    private static final int DEFAULT_WIN_WIDTH = 480;

    protected final JFrame formFrame;
    protected final QuestionPanel panel;

    public FormFrame(String _formTitle) {
        this(_formTitle, DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
    }

    public FormFrame(String _formTitle, int _width, int _height) {
        this.panel = new QuestionPanel();
        this.formFrame = new JFrame(_formTitle);
        this.panel.render(this.formFrame);

        this.formFrame.setSize(_width, _height);
        this.formFrame.setLocationRelativeTo(null);
        this.formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.formFrame.setResizable(false);
    }

    public void showForm() {
        this.formFrame.setVisible(true);
    }

    public void addWidget(JComponent _component) {
        this.panel.add(_component);
        this.formFrame.revalidate();
    }

    public void removeWidget(JComponent _component) {
        this.panel.remove(_component);
        this.formFrame.revalidate();
    }
}
