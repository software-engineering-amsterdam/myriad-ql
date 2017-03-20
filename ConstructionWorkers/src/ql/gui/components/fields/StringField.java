/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/fields/StringField.java.
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
import ql.gui.formenvironment.values.Value;
import ql.gui.formenvironment.values.StringValue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StringField extends Field {

    private StringValue value;

    StringField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        super(guiInterface, question, widget);
        resetValue();
        addListenerToField();
    }

    private void addListenerToField() {
        this.widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    StringValue newValue = (StringValue) widget.getValue();

                    if (!newValue.equals(value)) {
                        setValue(newValue);
                    }
                }
            }

        });
    }

    @Override
    public Value getValue() {
        return this.value;
    }

    @Override
    public void setValue(Value value) {
        this.value = (StringValue) value;
        widget.setValue(value);
        getNewChanges();
    }

    @Override
    public void resetValue() {
        StringValue emptyValue = new StringValue("");
        this.value = emptyValue;
        this.widget.setValue(emptyValue);
    }
}
