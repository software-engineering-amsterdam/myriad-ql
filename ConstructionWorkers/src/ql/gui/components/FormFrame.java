/**
 * FormFrame.java.
 */

package ql.gui.components;

import ql.gui.components.fields.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormFrame {

    private final JFrame formFrame;
    private final JPanel questionPanel;

    private List<Field> fields;

    private static final int DEFAULT_WIN_WIDTH = 480;
    private static final int DEFAULT_WIN_HEIGHT = 640;

    public FormFrame(String formTitle) {
        formFrame = new JFrame(formTitle);
        formFrame.setSize(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
        formFrame.setLocationRelativeTo(null);
        formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formFrame.setResizable(false);

        questionPanel = new JPanel();
        GridLayout questionPanelLayout = new GridLayout(0, 1);
        questionPanel.setLayout(questionPanelLayout);
        formFrame.add(questionPanel);

        this.fields = new ArrayList<>();
    }

    public void addToFields(Field field){
        if (!containsField(field)) {
            fields.add(field);
        }
    }

    public void removeFromFields(Field field){
        if (containsField(field)) {
            fields.remove(field);
        }
    }

    public Boolean containsField(Field field) {
        return fields.contains(field);
    }

    public void showForm() {
        formFrame.setVisible(true);
    }

    public void addWidget(JComponent widget) {
        questionPanel.add(widget);
        formFrame.revalidate();
    }

    public void removeWidget(JComponent widget) {
        questionPanel.remove(widget);
        formFrame.revalidate();
    }
}
