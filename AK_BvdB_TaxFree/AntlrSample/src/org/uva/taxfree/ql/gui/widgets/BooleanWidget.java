package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;

public class BooleanWidget extends Widget {
    private JCheckBox mCheckbox;

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
    public Value resolveValue() {
        return new BooleanValue(isTrue());
    }

    @Override
    public void callOnUpdate(FormListener listener) {
        mCheckbox.addActionListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public void updateValues(SymbolTable symbolTable) {
        writeToTable(symbolTable);
    }

    @Override
    protected void applyStyle(JPanel panel, JLabel label, QlsStyle qlsStyle) {
        super.applyStyle(panel, label, qlsStyle);
        // Only used for QLS
        qlsStyle.applyStyle(new BooleanType(), panel);
        qlsStyle.applyStyle(new BooleanType(), label);
        qlsStyle.applyStyle(new BooleanType(), mCheckbox);
    }
}
