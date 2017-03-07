package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ConditionNode;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

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
    public void addDeclaration(Set<NamedNode> set) {
        set.add(this);
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

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


    @Override
    public void addCalculation(Set<CalculatedField> set) {
        set.add(this);
    }

    public Set<String> getUsedVariables() {
        Set<String> usedVariables = new LinkedHashSet<>();
        mCondition.addUsedVariables(usedVariables);
        return usedVariables;
    }
}
