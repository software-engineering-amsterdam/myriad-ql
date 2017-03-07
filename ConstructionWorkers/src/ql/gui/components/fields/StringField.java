package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.Value;
import ql.gui.formenvironment.values.StringValue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by LGGX on 23-Feb-17.
 */

public class StringField extends Field {

    private StringValue value;

    public StringField(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        super(updates, question, widget);

        this.resetState();

        addListenerToField();
    }

    private void addListenerToField() {
        this.widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                StringValue newValue = (StringValue) widget.getValue();

                if (!newValue.equals(value)) {
                    setState(newValue);

                }
            }

        });
    }

    public boolean equalValues(Value value) {
        return value.equals(this.value);
    }

    public WidgetInterface getField() {
        return this.widget;
    }

    @Override
    public Value getState() {
        return this.value;
    }

    @Override
    public void setState(Value value) {
        this.widget.setValue(value);
        this.value = (StringValue) value;
        this.getNewChanges();
    }

    @Override
    public void resetState() {
        StringValue emptyValue = new StringValue("");
        this.value = emptyValue;
        this.widget.setValue(emptyValue);
    }

}
