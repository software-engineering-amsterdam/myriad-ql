package org.uva.taxfree.gui.widgets;

import org.uva.taxfree.gui.FormListener;
import org.uva.taxfree.model.environment.SymbolTable;

import javax.swing.*;
import java.util.List;

public abstract class Widget {
    private final JPanel mPanel;
    private final String mId;

    public Widget(String label, String id) {
        mPanel = createPanel(label);
        mId = id;
    }

    private JPanel createPanel(String label) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(label);
        widgetPanel.add(new JLabel(label));
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    public void registerToPanel(JPanel widgetPanel) {
        fillPanel(mPanel);
        widgetPanel.add(mPanel);
    }

    protected abstract void fillPanel(JPanel widgetPanel);

    public abstract String resolveValue();

    public abstract void callOnUpdate(FormListener listener);

    public abstract void updateValues(SymbolTable symbolTable);

    public void updateVisibility(List<String> visibleIds) {
        mPanel.setVisible(visibleIds.contains(mId));
    }


    protected void writeToTable(SymbolTable symbolTable) {
        symbolTable.updateValue(mId, resolveValue());
    }

    protected String readFromtable(SymbolTable symbolTable) {
        return symbolTable.resolveValue(mId);
    }
}
