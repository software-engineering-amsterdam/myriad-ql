package org.uva.taxfree.model;

import javax.swing.*;
import java.util.Set;

public abstract class NamedNode extends Node {

    private String mLabel;
    private String mId;

    public NamedNode(String label, String id) {
        mLabel = label;
        mId = id;
    }

    public JPanel getWidget() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.add(new JLabel(mLabel));
        fillPanel(widgetPanel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    protected abstract void fillPanel(JPanel parent);

    public String getId() {
        return mId;
    }

    protected void addQuestion(Set<NamedNode> set) {
        set.add(this);
    }

    public String getLabel() {
        return mLabel;
    }
}
