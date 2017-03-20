/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/fields/BooleanField.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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

    BooleanField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
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
        this.value = (BooleanValue) value;
        widget.setValue(value);
        getNewChanges();
    }

    @Override
    public void resetValue() {
        BooleanValue falseValue = new BooleanValue(false);
        value = falseValue;
        widget.setValue(falseValue);
    }
}
