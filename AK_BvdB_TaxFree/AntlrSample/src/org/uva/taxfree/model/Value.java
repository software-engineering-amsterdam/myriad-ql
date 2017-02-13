package org.uva.taxfree.model;

import javax.swing.*;

public abstract class Value {
    public JPanel getWidget(String label) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.add(new JLabel(label));
        fillPanel(widgetPanel);
        return widgetPanel;
    }

    protected abstract void fillPanel(JPanel panel);
}


