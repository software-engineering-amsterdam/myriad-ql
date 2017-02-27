package QL.GUI.GUIComponents.GUIWidgets;

import QL.semanticChecker.formDataStorage.valueData.values.MoneyValue;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.EventListener;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class MoneyTextField extends AbstractWidget{

    private static int COLUMNS = 7;
    private static int MIN_NUM = -1000000;
    private static int MAX_NUM = 1000000;

    private JFormattedTextField input;
    private NumberFormatter numberFormatter;

    public MoneyTextField(String _label) {
        JLabel label = new JLabel(_label);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        numberFormatter = new NumberFormatter(decimalFormat);
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
    public MoneyValue getValue() {
        String value = this.input.getText().replaceAll(",","");
        BigDecimal bd = new BigDecimal(value);
        return new MoneyValue(bd);
    }

    @Override
    public void setValue(Value value) {
        MoneyValue nvalue = (MoneyValue) value;
        this.input.setText(nvalue.getValue().toString());
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.input.setEditable(false);
        numberFormatter.setAllowsInvalid(true);
    }
}
