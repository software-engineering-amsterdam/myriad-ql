/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/widgets/Checkbox.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.widgets;

import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.EventListener;

public class Checkbox extends QLWidget {

    private JCheckBox checkBox;

    Checkbox(String questionLabel) {
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
    public void setReadOnly() {
        checkBox.setEnabled(false);
    }

    @Override
    public void addListener(EventListener listener) {
        checkBox.addItemListener((ItemListener) listener);
    }
}
