package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.Node;

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
    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    protected abstract void fillPanel(JPanel parent);

    public abstract String resolveValue();

    public String getId() {
        return mId;
    }

    @Override
    public void printId() {
        System.out.println("My name is: " + getId());
    }

    @Override
    public void addDeclaration(Set<NamedNode> set) {
        set.add(this);
    }

    public String getLabel() {
        return mPanel.getName();
    }
}
