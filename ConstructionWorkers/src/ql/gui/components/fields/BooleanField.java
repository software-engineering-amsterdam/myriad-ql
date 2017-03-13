/**
 * BooleanField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanField extends Field {

    private BooleanValue value;

    public BooleanField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        super(guiInterface, question, widget);
        resetValue();
        addListenerToField();
    }

    private void addListenerToField() {
        widget.addListener((ItemListener) event -> {
            BooleanValue newValue = new BooleanValue(false);

            if (event.getStateChange() == ItemEvent.SELECTED) {
                newValue = new BooleanValue(true);
            }

            setValue(newValue);
        });
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        widget.setValue(value);
        this.value = (BooleanValue) value;
        getNewChanges();
    }

    @Override
    public void resetValue() {
        BooleanValue falseValue = new BooleanValue(false);
        value = falseValue;
        widget.setValue(falseValue);
    }
}
