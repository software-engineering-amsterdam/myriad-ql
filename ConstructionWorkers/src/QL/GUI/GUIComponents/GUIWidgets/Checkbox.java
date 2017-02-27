package QL.GUI.GUIComponents.GUIWidgets;

import QL.semanticChecker.formDataStorage.valueData.values.BooleanValue;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.EventListener;

/**
 * Created by LGGX on 23-Feb-17.
 */
public class Checkbox extends AbstractWidget {

    private JCheckBox checkBox;

    public Checkbox(String _label) {
        this.checkBox = new JCheckBox(_label);
        this.checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        this.component.add(checkBox);
    }

    @Override
    public void addListener(EventListener listener) {
        checkBox.addItemListener((ItemListener) listener);
    }

    @Override
    public BooleanValue getValue() {
        return new BooleanValue(this.checkBox.isSelected());
    }

    @Override
    public void setValue(Value _value) {
        BooleanValue value = (BooleanValue) _value;
        this.checkBox.setSelected(value.getValue());
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.checkBox.setEnabled(false);
    }

}
