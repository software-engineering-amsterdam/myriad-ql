/**
 * FormFrame.java.
 */

package ql.gui.components;

import ql.gui.components.fields.Field;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FormFrame {

    private final JFrame formFrame;
    private final QuestionPanel panel;

    private List<Field> fields;

    private static final int DEFAULT_WIN_WIDTH = 480;
    private static final int DEFAULT_WIN_HEIGHT = 640;

    public FormFrame(String formTitle) {
        formFrame = new JFrame(formTitle);
        formFrame.setSize(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
        formFrame.setLocationRelativeTo(null);
        formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formFrame.setResizable(false);

        this.fields = new ArrayList<>();

        panel = new QuestionPanel();
        panel.render(formFrame);
    }

    public void addToFields(Field field){
        if (!containsField(field)) {
            this.fields.add(field);
        }
    }

    public void removeFromFields(Field field){
        if (containsField(field)) {
            this.fields.remove(field);
        }
    }

    public Boolean containsField(Field field) {
        return this.fields.contains(field);
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
