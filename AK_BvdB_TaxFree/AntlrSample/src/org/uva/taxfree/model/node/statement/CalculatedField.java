package org.uva.taxfree.model.node.statement;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.condition.ConditionNode;

import javax.swing.*;
import java.awt.*;

public abstract class CalculatedField extends NamedNode {
    ConditionNode mCondition;
    JTextField mTextField;

    public CalculatedField(String label, String id) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
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

    @Override
    public void setVisibility(boolean isVisible) {
        mTextField.setText(resolve());
        super.setVisibility(isVisible);
    }

    @Override
    public String resolve() {
        return mCondition.evaluate();
    }

}
