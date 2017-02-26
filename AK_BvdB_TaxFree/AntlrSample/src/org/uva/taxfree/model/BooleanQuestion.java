package org.uva.taxfree.model;

import javax.swing.*;

public class BooleanQuestion extends NamedNode {
    JCheckBox mCheckbox;
    public BooleanQuestion(String description, String id) {
        super(description, id);
        mCheckbox = new JCheckBox("", false);
    }

    @Override
    protected void fillPanel(JPanel parentPanel) {
        parentPanel.add(mCheckbox);
    }

    @Override
    public void printValue() {
        System.out.println("Value of bool: " + isTrue());
    }

    public boolean isTrue(){
        return mCheckbox.isSelected();
    }
}




