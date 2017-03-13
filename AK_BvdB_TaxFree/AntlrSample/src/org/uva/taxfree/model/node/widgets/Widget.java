package org.uva.taxfree.model.node.widgets;

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
        widgetPanel.add(mPanel);
    }

    protected abstract void fillPanel(JPanel panel);

    public void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    public abstract String resolveValue();

}
