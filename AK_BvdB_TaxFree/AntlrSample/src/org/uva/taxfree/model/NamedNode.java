package org.uva.taxfree.model;

import javax.swing.*;
import java.util.Set;

public abstract class NamedNode extends Node {
    private JPanel mPanel;
    private String mId;
    private String mLabel;

    public NamedNode(String label, String id) {
        mLabel = label;
        mId = id;
    }

    public void setVisibility(boolean isVisible) {
        getWidget().setVisible(isVisible);
        super.setVisibility(isVisible);
    }


    public JPanel getWidget() {
        if (mPanel == null) {
            mPanel = createPanel();
        }
        return mPanel;
    }

    private JPanel createPanel() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(mLabel);
        widgetPanel.add(new JLabel(mLabel));
        fillPanel(widgetPanel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    protected abstract void fillPanel(JPanel parent);

    public String toString() {
        return mId;
    }

    protected void addQuestion(Set<NamedNode> set) {
        set.add(this);
    }

    public String getLabel() {
        return mLabel;
    }

    protected abstract String resolve();
}
