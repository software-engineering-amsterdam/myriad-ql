package org.uva.taxfree.ql.gui.widgets;

import javax.swing.*;
import java.awt.*;

public class GuiComponent {
    private final JPanel mPanel;
    private final JLabel mLabel;

    public GuiComponent(String label, String id) {
        mLabel = new JLabel(label);
        mPanel = createPanel(id);
    }

    private JPanel createPanel(String id) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(id);
        widgetPanel.add(mLabel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    protected void setVisible(boolean isVisible) {
        mPanel.setVisible(isVisible);
    }

    protected void registerToPanel(JPanel mainPanel) {
        mainPanel.add(mPanel);
    }

    protected void add(JComponent childComponent) {
        mPanel.add(childComponent);
    }

    public void setForegroundColor(Color foregroundColor) {
        mLabel.setForeground(foregroundColor);
        for (int i = 0; mPanel.getComponentCount() > i; ++i) {
            mPanel.getComponent(i).setForeground(foregroundColor);
        }
    }

    public void setBackgroundColor(Color backgroundColor) {
        mPanel.setBackground(backgroundColor);
        for (int i = 0; mPanel.getComponentCount() > i; ++i) {
            mPanel.getComponent(i).setBackground(backgroundColor);
        }
    }

    public void setFontSize(int fontSize) {
        Font updatedFont = new Font(currentFont().getName(), currentFont().getStyle(), fontSize);
        applyFont(updatedFont);
    }

    public void setFontName(String fontName) {
        Font updatedFont = new Font(fontName, currentFont().getStyle(), currentFont().getSize());
        applyFont(updatedFont);
    }

    public void applyFont(Font updatedFont) {
        mLabel.setFont(updatedFont);
        for (int i = 0; mPanel.getComponentCount() > i; ++i) {
            mPanel.getComponent(i).setFont(updatedFont);
        }
    }

    private Font currentFont() {
        return mLabel.getFont();
    }

    public String getId() {
        return mPanel.getName();
    }
}
