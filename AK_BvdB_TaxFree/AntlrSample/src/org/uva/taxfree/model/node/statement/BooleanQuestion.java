package org.uva.taxfree.model.node.statement;

import javax.swing.*;

public class BooleanQuestion extends NamedNode {
    private final JCheckBox mCheckbox;

    public BooleanQuestion(String description, String id) {
        super(description, id);
        mCheckbox = new JCheckBox("", false);
    }

    @Override
    protected void fillPanel(JPanel parentPanel) {
        parentPanel.add(mCheckbox);
    }

    @Override
    public String resolveValue() {
        return String.valueOf(isTrue());
    }

    @Override
    public void printValue() {
        System.out.println("Value of bool: " + isTrue());
    }

    protected boolean isTrue() {
        return mCheckbox.isSelected();
    }
}




