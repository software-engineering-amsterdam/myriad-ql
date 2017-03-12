package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

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

    protected boolean isTrue() {
        return mCheckbox.isSelected();
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}




