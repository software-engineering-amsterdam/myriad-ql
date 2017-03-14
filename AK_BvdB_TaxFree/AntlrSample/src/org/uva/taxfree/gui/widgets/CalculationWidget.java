package org.uva.taxfree.gui.widgets;

import org.uva.taxfree.gui.FormListener;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import javax.swing.*;
import java.awt.*;

public class CalculationWidget extends Widget {
    private final ExpressionNode mExpression;
    private final JTextField mTextField;

    public CalculationWidget(String label, String id, ExpressionNode expression) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
        mExpression = expression;
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    @Override
    public String resolveValue() {
        return mExpression.resolveValue();
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mTextField.addPropertyChangeListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public void updateValues(SymbolTable symbolTable) {
        mTextField.setText(readFromtable(symbolTable));
    }
}
