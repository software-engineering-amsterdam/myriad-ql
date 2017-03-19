/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/fields/IntegerField.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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

    IntegerField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        super(guiInterface, question, widget);
        resetValue();
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
                            setValue(newValue);
                        } catch (Exception ex) {
                            System.out.println("Incorrect input value for integer field!");
                        }
                    }
                }
            }
        });
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        this.value = (IntegerValue) value;
        widget.setValue(value);
        getNewChanges();
    }

    @Override
    public void resetValue() {
        IntegerValue zeroValue = new IntegerValue(0);
        value = zeroValue;
        widget.setValue(zeroValue);
    }
}
