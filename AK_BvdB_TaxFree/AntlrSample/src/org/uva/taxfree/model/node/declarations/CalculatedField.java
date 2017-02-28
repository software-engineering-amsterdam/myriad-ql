package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ConditionNode;

import javax.swing.*;
import java.awt.*;

public abstract class CalculatedField extends NamedNode {
    private final ConditionNode mCondition;
    private final JTextField mTextField;

    public CalculatedField(String label, String id, ConditionNode condition) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
        mCondition = condition;
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    @Override
    public String toString() {
        return mCondition.toString();
    }

    @Override
    public void setVisible(boolean isVisible) {
        mTextField.setText(resolveValue());
        super.setVisible(isVisible);
    }

    @Override
    public String resolveValue() {
        return mCondition.evaluate();
    }

}
