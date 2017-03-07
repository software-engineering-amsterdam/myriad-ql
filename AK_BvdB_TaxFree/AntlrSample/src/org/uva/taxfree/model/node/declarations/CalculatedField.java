package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class CalculatedField extends NamedNode {
    private final ExpressionNode mCondition;
    private final JTextField mTextField;
    private final Type mType;

    public CalculatedField(String label, String id, Type type, ExpressionNode condition) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
        mType = type;
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
    public Type getType() {
        return mType;
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
