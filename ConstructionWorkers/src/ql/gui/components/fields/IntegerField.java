/**
 * IntegerField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerField extends Field {

    private IntegerValue value;

    public IntegerField(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        super(updates, question, widget);
        resetState();
        addListenerToField();
    }

    @Override
    public void resetState() {
        IntegerValue zeroValue = new IntegerValue(0);
        value = zeroValue;
        widget.setValue(zeroValue);
    }

    private void addListenerToField() {
        widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                IntegerValue newValue = (IntegerValue) widget.getValue();
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
        this.value = (IntegerValue) value;
        getNewChanges();
    }
}
