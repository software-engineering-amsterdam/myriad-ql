package org.uva.taxfree.model.node.widgets;

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
    public void setVisible(boolean isVisible) {
        mTextField.setText(resolveValue());
        super.setVisible(isVisible);
    }

    @Override
    public String resolveValue() {
        return mExpression.resolveValue();
    }
}
