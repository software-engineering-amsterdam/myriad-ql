package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.Widget;
import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by LGGX on 23-Feb-17.
 */

public class IntegerField extends Field {

    private IntegerValue value;

    public IntegerField(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates, question, widget);

        this.resetState();

        addListenerToField();
    }


    private void addListenerToField() {
        this.widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                IntegerValue newValue = new IntegerValue(0);

                newValue = (IntegerValue) widget.getValue();
                setState(newValue);

            }

        });
    }

    public Widget getField() {
        return this.widget;
    }

    @Override
    public Value getState() {
        return this.value;
    }

    @Override
    public void setState(Value value) {
        this.widget.setValue(value);
        this.value = (IntegerValue) value;
        this.getNewChanges();
    }

    @Override
    public void resetState() {
        IntegerValue zeroValue = new IntegerValue(0);
        this.value = zeroValue;
        this.widget.setValue(zeroValue);
    }
}
