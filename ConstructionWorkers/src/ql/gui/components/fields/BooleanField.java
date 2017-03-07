/**
 * BooleanField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BooleanField extends Field {

    private BooleanValue value;

    public BooleanField(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        super(updates, question, widget);
        resetState();
        addListenerToField();
    }

    @Override
    public void resetState() {
        BooleanValue falseValue = new BooleanValue(false);
        value = falseValue;
        widget.setValue(falseValue);
    }

    private void addListenerToField() {
        widget.addListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                BooleanValue newValue = new BooleanValue(false);

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    newValue = new BooleanValue(true);
                }

                setState(newValue);
            }
        });
    }

//    public WidgetInterface getField() {
//        return this.widget;
//    }

    @Override
    public Value getState() {
        return value;
    }

    @Override
    public void setState(Value value) {
        widget.setValue(value);
        this.value = (BooleanValue) value;
        getNewChanges();
    }
}
