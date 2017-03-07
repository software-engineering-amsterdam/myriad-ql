package ql.gui.components.widgets;

import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EventListener;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class MoneyTextField extends AbstractWidget{

    private static int COLUMNS = 7;

    private JTextField input;
    
    public MoneyTextField(String _label) {
        JLabel label = new JLabel(_label);

        this.input = new JTextField();
        this.input.setColumns(COLUMNS);

        this.component.add(label);
        this.component.add(input);
    }

    @Override
    public void addListener(EventListener listener) {
        input.addKeyListener((KeyListener) listener);
    }

    @Override
    public String getValue() {
        String value = this.input.getText();
        return value;
    }

    @Override
    public void setValue(Value value) {
        MoneyValue nvalue = (MoneyValue) value;
        this.input.setText(nvalue.getValue().toString());
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.input.setEditable(false);
        //defaultFormatter.setAllowsInvalid(true);
    }
}
