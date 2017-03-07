/**
 * MoneyField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.MoneyTextField;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class MoneyField extends Field {

    private MoneyValue value;

    public MoneyField(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        super(updates, question, widget);
        resetState();
        addListenerToField();
    }

    @Override
    public void resetState() {
        MoneyValue zeroValue = new MoneyValue(new BigDecimal(0.00));
        value = zeroValue;
        widget.setValue(zeroValue);
    }

    private void addListenerToField() {
        widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                boolean wasParsed = true;
                BigDecimal decimal = new BigDecimal(0.00);

                if(!(((MoneyTextField) widget).getInputText().equals(""))) {

                    try {
                        decimal = BigDecimal.valueOf(Double.parseDouble(((MoneyTextField) widget).getInputText()));
                        //new MoneyValue(decimal);
                    } catch (Exception ex) {
                        System.out.println("Incorrect input value for money field!");
                        wasParsed = false;
                    }

                    if (wasParsed) {
                        if (Math.max(0, decimal.stripTrailingZeros().scale()) > 2) {
                            System.out.println("Only two decimals are allowed!");
                        } else {
                            String value = ((MoneyTextField) widget).getInputText();
                            MoneyValue newValue = new MoneyValue(new BigDecimal(value));
                            setState(newValue);
                        }
                    }
                }
            }
        });
    }

//    public WidgetInterface getField() {
//        return this.widget;
//    }

    @Override
    public Value getState() {
        return value;
    }

    @Override
    public void setState(Value value) {
        widget.setValue(value);
        this.value = (MoneyValue) value;
        getNewChanges();
    }
}
