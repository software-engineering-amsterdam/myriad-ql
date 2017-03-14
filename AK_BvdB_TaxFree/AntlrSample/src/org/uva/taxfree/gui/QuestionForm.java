package org.uva.taxfree.gui;

import org.uva.taxfree.gui.widgets.Widget;
import org.uva.taxfree.model.environment.SymbolTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionForm implements FormListener {
    private final JFrame mFrame;
    private final JPanel mWidgetPanel;
    private final List<Widget> mWidgets;
    private final SymbolTable mSymbolTable;

    public QuestionForm(String caption, SymbolTable symbolTable) {
        mFrame = createFrame(caption);
        mWidgetPanel = createWidgetPanel();
        mFrame.add(mWidgetPanel);
        mWidgets = new ArrayList<>();
        mSymbolTable = symbolTable;
    }

    private JFrame createFrame(String caption) {
        JFrame frame = new JFrame(caption);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createWidgetPanel() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    public void show() {
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
        mFrame.setPreferredSize(new Dimension(640, 480));
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
        updateForm();
    }

    public void addWidget(Widget widget) {
        widget.registerToPanel(mWidgetPanel);
        widget.callOnUpdate(this);
        mWidgets.add(widget);
    }

    public void updateForm() {
        for (Widget w : mWidgets) {
            w.updateValues(mSymbolTable);
            w.updateVisibility(mSymbolTable.visibleIds());
        }
    }
}
