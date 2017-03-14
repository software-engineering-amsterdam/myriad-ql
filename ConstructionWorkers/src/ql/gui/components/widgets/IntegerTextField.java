/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/widgets/IntegerTextField.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class IntegerTextField extends QLWidget {

    private JTextField input;

    private static final int COLUMNS = 7;

    IntegerTextField(String questionLabel) {
        input = new JTextField();
        input.setColumns(COLUMNS);

        JLabel label = new JLabel(questionLabel);

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
        IntegerValue valueToSet = (IntegerValue) value;
        input.setText(Integer.toString(valueToSet.getValue()));
    }

    @Override
    public void setReadOnly() {
        input.setEditable(false);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }
}
