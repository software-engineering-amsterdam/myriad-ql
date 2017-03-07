/**
 * TextField.java.
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.StringValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class TextField extends AbstractWidget {

    private JTextField input;

    private static final int COLUMNS = 7;

    public TextField(String questionLabel) {
        JLabel label = new JLabel(questionLabel);

        input = new JTextField();
        input.setColumns(COLUMNS);

        component.add(label);
        component.add(input);
    }

    @Override
    public StringValue getValue() {
        return new StringValue(input.getText());
    }

    @Override
    public void setValue(Value value) {
        input.setText(((StringValue) value).getValue());
    }

    @Override
    public void setReadOnly(boolean isReadOnly) {
        input.setEditable(!isReadOnly);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }
}
