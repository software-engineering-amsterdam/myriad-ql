package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;

public class BooleanWidget extends Widget implements Resolvable {
    private JCheckBox mCheckbox;

    public BooleanWidget(String label, String id) {
        super(label, id);
        mCheckbox = new JCheckBox("", false);
    }

    @Override
    protected void fillPanel(GuiComponent parentPanel) {
        parentPanel.setValueComponent(mCheckbox);
    }

    @Override
    public Value resolveValue() {
        return new BooleanValue(mCheckbox.isSelected());
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
    protected void applyStyle(Widget component, QlsStyle qlsStyle) {
        super.applyStyle(component, qlsStyle);
        qlsStyle.applyStyle(new BooleanType(), component);
    }
}
