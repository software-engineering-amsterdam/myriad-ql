package GUI.GUIComponents.GUIFields;

import ASTnodes.statements.SimpleQuestion;
import GUI.GUIInterface;
import GUI.GUIComponents.GUIWidgets.Widget;
import semanticChecker.formDataStorage.valueData.values.MoneyValue;
import semanticChecker.formDataStorage.valueData.values.Value;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

/**
 * Created by LGGX on 23-Feb-17.
 */

public class MoneyField extends Field {

    private MoneyValue value;
    private Widget feedback;

    public MoneyField(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates, question, widget);

        this.resetState();

        addListenerToField();
    }

    private void addListenerToField() {
        this.widget.addListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                MoneyValue newValue = new MoneyValue(BigDecimal.valueOf(0.00));

                newValue = (MoneyValue) widget.getValue();
                setState(newValue);


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
