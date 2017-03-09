/**
 * IntegerField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.IntegerTextField;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntegerField extends Field {

    private IntegerValue value;

    public IntegerField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        super(guiInterface, question, widget);
        resetState();
        addListenerToField();
    }

    private void addListenerToField() {
        widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!(((IntegerTextField) widget).getInputText().equals(""))) {

                        try {
                            IntegerValue newValue = (IntegerValue) widget.getValue();
                            setState(newValue);
                        } catch (Exception ex) {
                            System.out.println("Incorrect input value for integer field!");
                        }

                    }

                }
            }
        });
    }

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

    @Override
    public void resetState() {
        IntegerValue zeroValue = new IntegerValue(0);
        value = zeroValue;
        widget.setValue(zeroValue);
    }

}
