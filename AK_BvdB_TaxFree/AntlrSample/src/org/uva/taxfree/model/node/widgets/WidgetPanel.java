package org.uva.taxfree.model.node.widgets;

import javax.swing.*;

public class WidgetPanel {
    public WidgetPanel() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        fillWidgetPanel(widgetPanel);
        widgetPanel.setVisible(true);
    }

    public void fillWidgetPanel(JPanel widgetPanel){
        throw new RuntimeException("Unimplemented");
    }
}
