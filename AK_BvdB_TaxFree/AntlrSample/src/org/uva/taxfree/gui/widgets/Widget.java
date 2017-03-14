package org.uva.taxfree.gui.widgets;

import org.uva.taxfree.gui.FormListener;
import org.uva.taxfree.model.environment.SymbolTable;

import javax.swing.*;

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
        widgetPanel.setVisible(false);
        return widgetPanel;
    }

    public void registerToPanel(JPanel widgetPanel) {
        fillPanel(mPanel);
        widgetPanel.add(mPanel);
    }

    protected abstract void fillPanel(JPanel widgetPanel);

    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    public abstract String resolveValue();

    public abstract void callOnUpdate(FormListener listener);

    public abstract void update(SymbolTable symbolTable);

    protected void writeToTable(SymbolTable symbolTable){
        symbolTable.updateValue(mId, resolveValue());
    }

    protected String readFromtable(SymbolTable symbolTable){
        return symbolTable.resolveValue(mId);
    }
}
