package GUI.widgets;

import semanticChecker.formDataStorage.valueData.values.IntegerValue;
import semanticChecker.formDataStorage.valueData.values.Value;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.EventListener;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class IntegerTextField extends AbstractWidget{

    private static int COLUMNS = 7;
    private static int MIN_NUM = -1000000;
    private static int MAX_NUM = 1000000;

    private JFormattedTextField input;
    private NumberFormatter numberFormatter;

    public IntegerTextField(String _label) {
        JLabel label = new JLabel(_label);

        NumberFormat intFormat = NumberFormat.getIntegerInstance();
        intFormat.setGroupingUsed(false);
        intFormat.setParseIntegerOnly(true);

        numberFormatter = new NumberFormatter(intFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(MIN_NUM);
        numberFormatter.setMaximum(MAX_NUM);
        numberFormatter.setOverwriteMode(true);

        this.input = new JFormattedTextField(numberFormatter);

        this.input.setColumns(COLUMNS);

        this.component.add(label);
        this.component.add(input);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }

    @Override
    public IntegerValue getValue() {
        return new IntegerValue(Integer.parseInt(this.input.getText()));
    }

    @Override
    public void setValue(Value value) {
        IntegerValue nvalue = (IntegerValue) value;
        this.input.setText(Integer.toString(nvalue.getValue()));
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.input.setEditable(false);
        numberFormatter.setAllowsInvalid(true);
    }
}
