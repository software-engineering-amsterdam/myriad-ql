package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.types.Type;

import javax.swing.*;
import java.util.Set;

public abstract class NamedNode extends Node {
    private final JPanel mPanel;
    private final String mId;

    public NamedNode(String label, String id) {
        super();
        mId = id;
        mPanel = createPanel(label);
    }

    private JPanel createPanel(String label) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(label);
        widgetPanel.add(new JLabel(label));
        widgetPanel.setVisible(false);
        return widgetPanel;
    }

    public JPanel getWidget() {
        fillPanel(mPanel);
        return mPanel;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
       symbolTable.addDeclaration(this);
    }

    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    protected abstract void fillPanel(JPanel parent);

    public abstract String resolveValue();

    public String getId() {
        return mId;
    }

    public String getLabel() {
        return mPanel.getName();
    }

    public abstract Type getType();

    protected void getDependencies(Set<String> depencencies){
        depencencies.add(mId);
    }
}
