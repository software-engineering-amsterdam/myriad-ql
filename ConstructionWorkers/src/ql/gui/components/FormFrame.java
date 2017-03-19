/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/FormFrame.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components;

import ql.gui.components.fields.Field;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FormFrame {

    private List<Field> fields;

    private final JFrame formFrame;
    private final JPanel questionPanel;

    private static final int DEFAULT_WIN_WIDTH = 480;
    private static final int DEFAULT_WIN_HEIGHT = 540;

    public FormFrame(String formTitle) {
        fields = new ArrayList<>();

        formFrame = new JFrame(formTitle);
        formFrame.setSize(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
        formFrame.setLocationRelativeTo(null);
        formFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        formFrame.setResizable(true);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
        formFrame.add(questionPanel);
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

    private Boolean containsField(Field field) {
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
