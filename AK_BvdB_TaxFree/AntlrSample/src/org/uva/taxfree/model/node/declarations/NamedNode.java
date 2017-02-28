package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.Node;

import javax.swing.*;
import java.util.Set;

public abstract class NamedNode extends Node {
    private final String mLabel;
    private final String mId;

    public NamedNode(String label, String id) {
        super();
        mLabel = label;
        mId = id;
    }

    private JPanel createPanel(String label) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(label);
        widgetPanel.add(new JLabel(label));
        fillPanel(widgetPanel);
        widgetPanel.setVisible(false);
        return widgetPanel;
    }

    public JPanel getWidget() {
        return createPanel(mLabel);
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
}
