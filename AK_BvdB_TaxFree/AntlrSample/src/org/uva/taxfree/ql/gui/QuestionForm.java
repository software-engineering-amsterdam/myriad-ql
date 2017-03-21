package org.uva.taxfree.ql.gui;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    mSymbolTable.export(caption + ".txdata");
                } catch (IOException error) {
                    MessageWindow.showMessage("(QuestionForm.java:38): Unable to write results to file:\r\n" + error.getMessage());
                }
            }
        });
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
        for (Widget widget : mWidgets) {
            widget.updateValues(mSymbolTable);
        }

        for (Widget widget : mWidgets) {
            widget.updateVisibility(mSymbolTable);
        }
    }

    public void applyStyle(QlsStyle qlsStyle) {
        for (Widget w : mWidgets) {
            w.updateStyle(qlsStyle);
        }
    }
}
