/**
 * MoneyTextField.java.
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.EventListener;

public class MoneyTextField extends QLWidget {

    private JTextField input;

    private static final int COLUMNS = 7;
    
    public MoneyTextField(String questionLabel) {
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
    public Value getValue() {
        return new MoneyValue(new BigDecimal(input.getText()));
    }

    @Override
    public void setValue(Value value) {
        MoneyValue valueToSet = (MoneyValue) value;
        input.setText(valueToSet.getValue().toString());
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
