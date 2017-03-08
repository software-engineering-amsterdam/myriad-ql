/**
 * IntegerTextField.java.
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.EventListener;

public class IntegerTextField extends AbstractWidget{

    private JTextField input;

    private static final int COLUMNS = 7;

    public IntegerTextField(String questionLabel) {

        JLabel label = new JLabel(questionLabel);
        input = new JTextField();
        input.setColumns(COLUMNS);

        component.add(label);
        component.add(input);
    }

    public String getInputText() {
        return input.getText();
    }

    @Override
    public IntegerValue getValue() {
        return new IntegerValue(Integer.parseInt(input.getText()));
    }

    @Override
    public void setValue(Value value) {
        input.setText(Integer.toString(((IntegerValue) value).getValue()));
    }

    @Override
    public void setReadOnly(boolean isReadonly) {
        input.setEditable(!isReadonly);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }
}
