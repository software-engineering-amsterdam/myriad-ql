package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.Widget;
import ql.gui.formenvironment.values.MoneyValue;
import ql.gui.formenvironment.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

/**
 * Created by LGGX on 23-Feb-17.
 */

public class MoneyField extends Field {

    private MoneyValue value;

    public MoneyField(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates, question, widget);

        this.resetState();

        addListenerToField();
    }

    private void addListenerToField() {
        this.widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                boolean wasParsed = true;

                MoneyValue newValue = new MoneyValue(BigDecimal.valueOf(0.00));

                BigDecimal decimal = new BigDecimal(0.00);

                if (!widget.getValue().equals("")) {
                    try {
                        decimal = BigDecimal.valueOf(Double.parseDouble((String) widget.getValue()));
                        new MoneyValue(decimal);
                    } catch (Exception ex) {
                        System.out.println("Incorrect input value for Money field!");
                        wasParsed = false;
                    }

                    if (wasParsed) {
                        if (Math.max(0, decimal.stripTrailingZeros().scale()) > 2) {
                            System.out.println("Only two decimals are allowed!");
                        } else {
                            String value = (String) widget.getValue();
                            BigDecimal bd = new BigDecimal(value);
                            newValue = new MoneyValue(bd);
                            setState(newValue);
                        }
                    }
                }

            }

        });
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
        this.value = (MoneyValue) value;
        this.getNewChanges();
    }

    @Override
    public void resetState() {
        MoneyValue zeroValue = new MoneyValue(new BigDecimal(0.00));
        this.value = zeroValue;
        this.widget.setValue(zeroValue);
    }

}
