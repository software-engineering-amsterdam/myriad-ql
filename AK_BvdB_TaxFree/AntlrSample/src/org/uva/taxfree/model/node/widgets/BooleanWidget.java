package org.uva.taxfree.model.node.widgets;

import javax.swing.*;

public class BooleanWidget extends Widget {
    private final JCheckBox mCheckbox;

    public BooleanWidget(String label, String id) {
        super(label, id);
        mCheckbox = new JCheckBox("", false);
    }

    @Override
    protected void fillPanel(JPanel parentPanel) {
        parentPanel.add(mCheckbox);
    }

    protected boolean isTrue() {
        return mCheckbox.isSelected();
    }

    @Override
    public String resolveValue() {
        return String.valueOf(isTrue());
    }

}
