package QL.GUI.GUIComponents.GUIFields;

import QL.ASTnodes.statements.SimpleQuestion;
import QL.GUI.GUIInterface;
import QL.GUI.GUIComponents.GUIWidgets.Widget;
import QL.semanticChecker.formDataStorage.valueData.values.BooleanValue;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by LGGX on 23-Feb-17.
 */
public class BooleanField extends Field {

    private BooleanValue value;

    public BooleanField(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates, question, widget);

        this.resetState();

        addListenerToField();

    }

    private void addListenerToField() {
        this.widget.addListener(new ItemListener() {

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

    @Override
    public void resetState() {
        BooleanValue falseValue = new BooleanValue(false);
        this.value = falseValue;
        this.widget.setValue(falseValue);
    }

    public boolean equalValues(Value value) {
        return value.equals(this.value);
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
        this.value = (BooleanValue) value;
        this.getNewChanges();

    }
}
