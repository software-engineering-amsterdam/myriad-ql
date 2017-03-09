/**
 * Checkbox.java.
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.EventListener;

public class Checkbox extends QLWidget {

    private JCheckBox checkBox;

    public Checkbox(String questionLabel) {
        checkBox = new JCheckBox(questionLabel);
        checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        component.add(checkBox);
    }

    @Override
    public BooleanValue getValue() {
        return new BooleanValue(checkBox.isSelected());
    }

    @Override
    public void setValue(Value value) {
        BooleanValue valueToSet = (BooleanValue) value;
        checkBox.setSelected(valueToSet.getValue());
    }

    @Override
    public void setReadOnly(boolean isReadOnly) {
        checkBox.setEnabled(!isReadOnly);
    }

    @Override
    public void addListener(EventListener listener) {
        checkBox.addItemListener((ItemListener) listener);
    }
}
