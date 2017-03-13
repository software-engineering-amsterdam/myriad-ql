/**
 * MoneyField.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.MoneyTextField;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class MoneyField extends Field {

    private MoneyValue value;

    public MoneyField(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        super(guiInterface, question, widget);
        resetValue();
        addListenerToField();
    }

    private void addListenerToField() {
        widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String inputText = ((MoneyTextField) widget).getInputText();

                    if (!(inputText.equals(""))) {
                        try {
                            BigDecimal decimal = BigDecimal.valueOf(Double.parseDouble(inputText));

                            if (Math.max(0, decimal.stripTrailingZeros().scale()) > 2) {
                                System.out.println("Only two decimals are allowed!");
                            } else {
                                MoneyValue newValue = (MoneyValue) widget.getValue();
                                setValue(newValue);
                            }
                        } catch (Exception ex) {
                            System.out.println("Incorrect input value for money field!");
                        }
                    }
                }
            }
        });
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public void setValue(Value value) {
        widget.setValue(value);
        this.value = (MoneyValue) value;
        getNewChanges();
    }

    @Override
    public void resetValue() {
        MoneyValue zeroValue = new MoneyValue(new BigDecimal(0.00));
        value = zeroValue;
        widget.setValue(zeroValue);
    }
}
