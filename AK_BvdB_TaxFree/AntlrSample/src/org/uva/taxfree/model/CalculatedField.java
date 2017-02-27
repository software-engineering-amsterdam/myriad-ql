package org.uva.taxfree.model;

import javax.swing.*;

public abstract class CalculatedField extends NamedNode {
    ConditionNode mCondition;
    JTextField mTextField;

    public CalculatedField(String label, String id) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
    }

    @Override
    protected void fillPanel(JPanel parent) {

    }

    @Override
    public void addChild(Node child) {
        assert mCondition == null;
        mCondition = (ConditionNode) child;
    }

    @Override
    public String toString() {
        return mCondition.toString();
    }
}
