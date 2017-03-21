package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.values.Value;

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
    public Value resolveValue() {
        return mExpression.evaluate();
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mTextField.addPropertyChangeListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public void updateValues(SymbolTable symbolTable) {
        mTextField.setText(readFromTable(symbolTable));
    }
}
