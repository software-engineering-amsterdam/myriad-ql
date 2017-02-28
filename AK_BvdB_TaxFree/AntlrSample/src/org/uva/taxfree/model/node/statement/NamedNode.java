package org.uva.taxfree.model.node.statement;

import org.uva.taxfree.model.node.Node;

import javax.swing.*;

public abstract class NamedNode extends Node {
    private final JPanel mPanel;
    private final String mId;
    private final String mLabel;

    public NamedNode(String label, String id) {
        super();
        mLabel = label;
        mId = id;
        mPanel = createPanel();
    }

    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    private JPanel createPanel() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(mLabel);
        widgetPanel.add(new JLabel(mLabel));
        fillPanel(widgetPanel);
        widgetPanel.setVisible(false);
        return widgetPanel;
    }

    public JPanel getWidget() {
        return mPanel;
    }

    protected abstract void fillPanel(JPanel parent);

    public String toString() {
        return mId;
    }

    public String getLabel() {
        return mLabel;
    }

    public abstract String resolveValue();
}
