package GUI.GUIComponents.GUIWidgets;

import semanticChecker.formDataStorage.valueData.values.StringValue;
import semanticChecker.formDataStorage.valueData.values.Value;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.EventListener;

/**
 * Created by LGGX on 23-Feb-17.
 */

public class TextField extends AbstractWidget {

    private JTextField input;

    public TextField(String _label) {
        JLabel label = new JLabel(_label);
        this.input = new JTextField();

        this.input.setColumns(7);

        this.component.add(label);
        this.component.add(input);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }

    @Override
    public StringValue getValue() {
        return new StringValue(this.input.getText());
    }

    @Override
    public void setValue(Value value) {
        StringValue nvalue = (StringValue) value;
        this.input.setText(nvalue.getValue());
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.input.setEditable(false);
        this.input.setEditable(false);
    }


}
