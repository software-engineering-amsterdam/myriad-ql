/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/widgets/TextField.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.StringValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class TextField extends QLWidget {

    private JTextField input;

    private static final int COLUMNS = 6;

    TextField(String questionLabel) {
        input = new JTextField();
        input.setColumns(COLUMNS);

        JLabel label = new JLabel(questionLabel);

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
    public void setReadOnly() {
        input.setEditable(false);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }
}
