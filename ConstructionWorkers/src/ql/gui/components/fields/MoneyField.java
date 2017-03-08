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

    public MoneyField(GUIInterface guiInterface, SimpleQuestion question, WidgetInterface widget) {
        super(guiInterface, question, widget);
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

                if(e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!(((MoneyTextField) widget).getInputText().equals(""))) {

                        try {
                            BigDecimal decimal = BigDecimal.valueOf(Double.parseDouble(((MoneyTextField) widget).getInputText()));

                            if (Math.max(0, decimal.stripTrailingZeros().scale()) > 2) {
                                System.out.println("Only two decimals are allowed!");
                            } else {
                                String value = ((MoneyTextField) widget).getInputText();
                                MoneyValue newValue = new MoneyValue(new BigDecimal(value));
                                setState(newValue);
                            }

                        } catch (Exception ex) {
                            System.out.println("Incorrect input value for money field!");

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
