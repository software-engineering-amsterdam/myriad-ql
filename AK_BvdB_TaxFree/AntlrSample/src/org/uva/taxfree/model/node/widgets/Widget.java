package org.uva.taxfree.model.node.widgets;

import javax.swing.*;

public abstract class Widget {
    private final JPanel mPanel;

    public Widget(String label) {
        mPanel = createPanel(label);
    }

    private JPanel createPanel(String label) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(label);
        widgetPanel.add(new JLabel(label));
        widgetPanel.setVisible(false);
        return widgetPanel;
    }

    public void create() {
        fillPanel(mPanel);
    }

    protected abstract void fillPanel(JPanel panel);

    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    public abstract String resolveValue();

}
