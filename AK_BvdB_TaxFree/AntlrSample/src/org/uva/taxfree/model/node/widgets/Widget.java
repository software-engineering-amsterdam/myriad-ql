package org.uva.taxfree.model.node.widgets;

import org.uva.taxfree.model.node.expression.ExpressionNode;

import javax.swing.*;

public abstract class Widget {
    private final JPanel mPanel;
    private final String mId;
    private ExpressionNode mExpression;

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
}
